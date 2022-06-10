# Basic default options

Create a file called `defaultoptions.txt` next to where `options.txt` would go. When Minecraft is creating the initial options.txt file, it will have the contents of defaultoptions.txt instead of being empty.

**Make sure to include the `version:2975` key from `options.txt` in your `defaultoptions.txt`**. Otherwise the game will assume `options.txt` comes from a super old version of Minecraft (pre-Datafixer) and will explode when parsing options.

This will not set the default values of keybindings. Keybindings will appear in the options screen with an activated "reset" button and clicking it will reset to the modder-intended value. This is intended.