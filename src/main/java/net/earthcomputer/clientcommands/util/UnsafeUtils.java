package net.earthcomputer.clientcommands.util;

import com.mojang.logging.LogUtils;
import net.minecraft.util.Util;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

/**
 * @author Gaming32
 */
public final class UnsafeUtils {

    private UnsafeUtils() {
    }

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final MethodHandles.@Nullable Lookup IMPL_LOOKUP = Util.make(() -> {
        try {
            final Field implLookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            implLookupField.setAccessible(true);
            return (MethodHandles.Lookup) implLookupField.get(null);
        } catch (Exception e) {
            LOGGER.error("Could not access IMPL_LOOKUP", e);
            return null;
        }
    });

    public static MethodHandles.@Nullable Lookup getImplLookup() {
        return IMPL_LOOKUP;
    }
}
