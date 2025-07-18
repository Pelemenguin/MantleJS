package pelemenguin.mantlejs.content.book.transformer;

import dev.latvian.mods.kubejs.KubeJS;
import net.minecraft.resources.ResourceLocation;

public class TransformerInitialization {
    
    public static void initTransformer() {
        for (String k : TransformerBuilder.TRANSFORMER_BUILDERS.keySet()) {
            MantleJSTransformer.TRANSFORM_FUNCTIONS.put(new ResourceLocation(KubeJS.appendModId(k)), TransformerBuilder.TRANSFORMER_BUILDERS.get(k).transformer);
        }
    }

}
