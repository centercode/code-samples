#!/bin/bash
# A script for OLAP engine's easy troubleshooting.
set -euo pipefail

audit_format() {
  cat - \
    |grep 'IsQuery=true' \
    |sed -nE 's/^([0-9]{4}-[0-9]{2}-[0-9]{2}) ([0-9]{2}:[0-9]{2}:[0-9]{2}).*Time=([^|]*).*ScanBytes=([^|]*).*CpuCostNs=([^|]*).*MemCostBytes=([^|]*).*QueryId=([^|]*).*$/\1T\2\t\7\t\3\t\4\t\5\t\6/p'
}

audit() {
  local sortCol=
  while [[ $# -ge 2 ]]; do
    case $1 in
      -s)
        case $2 in
          "m") sortCol=6 ;;
          "c") sortCol=5 ;;
          "b") sortCol=4 ;;
          "t") sortCol=3 ;;
          "*") echo "unknown sort column: $2" && exit 1 ;;
        esac
        shift 2
        ;;
      *) ;;
    esac
  done
  echo -e 'Timestamp\tQueryId\tTime\tScanBytes\tCpuCost\tMemCost'
  if [[ -z "$sortCol" ]]; then
    cat - | audit_format
  else
    cat - | audit_format | sort -rnk${sortCol}
  fi
}

usage() {
  echo "Usage:olap.sh <command> [options]"
  echo "  commands:"
  echo "    audit: Parse and format fe audit log from stdin, you can filter audit log such as 'grep IsQuery=' before input."
  echo "      example: cat /path/to/fe.audit.log|grep IsQuery=|olap.sh audit -s m"
  echo "      options:"
  echo "       -s [m|c|b|t]: sort the output by column."
  echo "          m: Memory cost in bytes."
  echo "          c: CPU cost in nanos."
  echo "          b: Bytes of scanned."
  echo "          t: Time cost in millis."
}

main() {
  if [[ $# -gt 0 ]]; then
    case $1 in
      audit) shift && audit $@ ;;
      *) usage && exit 1;;
    esac
  fi
}

main $@