#!/bin/bash
# 清理ubuntu系统的缓存
#
rm $HOME/.cache/thumbnails/normal/*\
    $HOME/.cache/thumbnails/large/*\
    $HOME/.thumbnails/normal/*\
    $HOME/.cache/thumbnails/fail/* -rf &> /dev/null

# chrome
# ~/.cache/google-chrome/Default/Cache/
# firefox
# ~/.cache/mozilla/firefox
