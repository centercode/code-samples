#!/bin/bash
set -eux

echo "Main process id: $$"

# Will generate new process, same to call './sub.sh'
sh ./sub.sh

# Will NOT generate new process and exit following commands
# same to call 'exec ./sub.sh'
exec sh ./sub.sh

# Will NOT generate new process and exit following commands
# same to call 'exec ./sub.sh'
# same to call 'eval exec sh ./sub.sh'
eval sh ./sub.sh

