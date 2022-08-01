# PaperMCPlugin-GuiApi

1. [Overview](#link_overview)
2. [Quickstart](#link_quickstart)

## <a name="link_overview"></a>Overview
Make a Minecraft-Gui with PaperMC as fast and effective as possible.

## <a name="link_quickstart"></a>Quickstart
- Clone the Repository
- run ```.\gradlew build```
- copy the file ```.\build\lips\PaperMcPlugin-GuiApi-1.0.jar``` into your plugin folder
- add the file ```.\build\lips\PaperMcPlugin-GuiApi-1.0.jar``` to your Java project as Library

Now you can create an InventoryGui and add GuiObjects like this.
```java
//Create an InventoryGui
InventoryGui inventoryGui = new InventoryGui(4, Component.text("MyInventoryGui"));

//Create an CustomGuiObject with an Bread as an Item
GuiObject guiObject = new GuiObject(new ItemStack(Material.BREAD));

//Add the CustomGuiObject to the InventoryGui
inventoryGui.addGuiObject(0, guiObject);
```
Have Fun! :)

