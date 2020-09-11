# Simple-Gui
A In-game gui builder for spigot minecraft

This Spigot plugin is very new and may have many issues, use at your own discretion
Will update with more info once project is in a more user friendly state

Help Key

<Menu-Name> Name of menu this is synonymous with the key names in the config
<Size> This is the size of the menu it must be under 54 and divisable by 9
<ActionType> Thes are commands and menu they specify whether to execute your action as a menu or as a command
  
Commands

/setsize <Menu-Name> <Size> - Sets Size of any menu

/createmenu <Menu-Name> <Size> - Create a new menu with a size

/setclose <Menu-Name> <true/false> - set a slots action to close the menu after being seleted

/itemset <Menu-Name> <Slot> - set an item to a slot in a menu

/menuaction <Menu-Name> <Slot> <ActionType> <Action> - set an action to a slot to execute when slot is selected
  
/setplayerjoin <Menu-Name> <True/False> - set menu to open on player join
  
/removecmd <Menu-Name> <Slot> - remove a command from a menu
  
/reloadcmd - reload the config

/<Menu-Name> - if player has permission they can open any menu by its name if it was created before the last restart 

/openmenu <Menu-Name> - A player with permission can open any menu by name no restarting needed
