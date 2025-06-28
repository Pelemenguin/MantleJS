package pelemenguin.mantlejs.plugin;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import pelemenguin.mantlejs.event.MantleJSEventGroup;

public class MantleJSPlugin extends KubeJSPlugin {
    
    @Override
    public void registerEvents() {
        MantleJSEventGroup.GROUP.register();
    }

}
