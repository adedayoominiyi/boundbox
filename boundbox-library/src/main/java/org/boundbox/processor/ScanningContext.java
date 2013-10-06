package org.boundbox.processor;

import lombok.Data;
import lombok.NonNull;

import org.boundbox.model.ClassInfo;


/**
 * This class is used during annotation processing. It contains informations related 
 * to annotation tree exploration such as the current {@link ClassInfo} being explored, its visibility, etc.
 * @author SNI
 */
@Data
public class ScanningContext {
    
    /** Current class being visited. */
    @NonNull private ClassInfo currentClassInfo;
    /** Current inheritance level. 0 means the BoundClass, 1 its super class, etc. */
    private int inheritanceLevel = 0;
    /** Whether or not we crossed a static context. */
    private boolean isStatic = false;
    /** Whether or not the class being visited is still visible to the BoundBox. */
    private boolean isVisible = true;
}