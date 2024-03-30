#include "zookeeper.h"
#include <cerrno>
#include <iostream>

static const char *state2String(int state) {
  if (state == 0)
    return "CLOSED_STATE";
  if (state == ZOOAPI::ZOO_CONNECTING_STATE)
    return "CONNECTING_STATE";
  if (state == ZOO_ASSOCIATING_STATE)
    return "ASSOCIATING_STATE";
  if (state == ZOO_CONNECTED_STATE)
    return "CONNECTED_STATE";
  if (state == ZOO_EXPIRED_SESSION_STATE)
    return "EXPIRED_SESSION_STATE";
  if (state == ZOO_AUTH_FAILED_STATE)
    return "AUTH_FAILED_STATE";

  return "INVALID_STATE";
}

static const char *type2String(int state) {
  if (state == ZOOAPI::ZOO_CREATED_EVENT)
    return "CREATED_EVENT";
  if (state == ZOO_DELETED_EVENT)
    return "DELETED_EVENT";
  if (state == ZOO_CHANGED_EVENT)
    return "CHANGED_EVENT";
  if (state == ZOO_CHILD_EVENT)
    return "CHILD_EVENT";
  if (state == ZOO_SESSION_EVENT)
    return "SESSION_EVENT";
  if (state == ZOO_NOTWATCHING_EVENT)
    return "NOTWATCHING_EVENT";

  return "UNKNOWN_EVENT_TYPE";
}

void global_watcher(zhandle_t *zh, int type, int state, const char *path, void *watcherCtx) {
  fprintf(stderr, "Watcher %s state = %s", type2String(type), state2String(state));
}

int main() {
  std::cout << "Hello, Zookeeper: " << ZOO_ERRORS::ZOK << std::endl;
  const char *host_port = "localhost:2181";
  zhandle_t *zh = ZOOAPI::zookeeper_init(host_port, global_watcher, 30000, nullptr, nullptr, 0);
  if (!zh) {
    fprintf(stderr, "failed to init. [errno=%d]\n", errno);
    exit(1);
  }
  const char *path = "/yangxiang";
  int flags = ZOO_EPHEMERAL | ZOO_SEQUENCE;
  int rc = zoo_acreate(zh, path, "", 0, &ZOO_OPEN_ACL_UNSAFE, flags, nullptr, "");
  if (rc != ZOO_ERRORS::ZOK) {
    fprintf(stderr, "failed to create node.[err=%d]\n", rc);
    exit(2);
  }

  if ((rc = zookeeper_close(zh)) != ZOO_ERRORS::ZOK) {
    fprintf(stderr, "failed to close zookeeper.[err=%d]\n", rc);
    exit(3);
  }

  return 0;
}
