# Coordinated Command Blocks

Adds a button to convert between Relative and Absolute coordinates in Command Blocks

for instance a command block at 10, 10, 10 with `setblock ~1 ~2 ~3 stone` would convert into `setblock 11 12 13 stone`, then clicking it again would turn the coords back into `~1 ~2 ~3`

coordinate conversions are always relative to the position of the command block, so it may have unintended consequences when doing `execute positioned` or `execute at`

![Gif showing the mod in action](https://cdn.modrinth.com/data/qkkSAgRL/images/becc56b518161b5474c5dcd18b39d1252151f82a.gif)