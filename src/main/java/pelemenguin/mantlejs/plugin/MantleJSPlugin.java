package pelemenguin.mantlejs.plugin;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import pelemenguin.mantlejs.content.book.transformer.BuiltinTransformer;
import pelemenguin.mantlejs.event.MantleJSEventGroup;

public class MantleJSPlugin extends KubeJSPlugin {
    
    @Override
    public void registerEvents() {
        MantleJSEventGroup.GROUP.register();
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("BuiltinTransformer", BuiltinTransformer.class);
   }

}
