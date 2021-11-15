# PaperMCPlugin-GuiApi

1. [Overview](#link_overview)
2. [Quickstart](#link_quickstart)

## <a name="link_overview"></a>Overview
Make a Minecraft-Gui with PaperMC as fast and effective as possible.

## <a name="link_quickstart"></a>Quickstart
To start download the .jar file and put it into your plugin folder on your PaperMC server and also add it to your Java project as Library.
Now you can create an InventoryGui and add GuiObjects like this.
```java
//Create an InventoryGui
InventoryGui inventoryGui = new InventoryGui(4, Component.text("MyInventoryGui"));

//Create an CustomGuiObject with an Bread as an Item
CustomGuiObject customGuiObject = new CustomGuiObject(new ItemStack(Material.BREAD));

//Add the CustomGuiObject to the InventoryGui
inventoryGui.addGuiObject(0, customGuiObject);
```
Have Fun! :)

