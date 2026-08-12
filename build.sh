#!/usr/bin/env bash
set -e

mvn clean install

rm -rf plugins
mkdir -p plugins

for module in Common CommonBullet CommonAsteroids Player Enemy Asteroids Bullet Collision Score; do
    cp "$module"/target/"$module"-*.jar plugins/
done

ls -1 plugins/
