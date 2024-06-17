package lesson2.concerttickets.utils;

import lesson2.concerttickets.model.annotation.NullableWarning;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NullableWarningManager {

    public static void checkFields(Object o) {
        List<Field> fields = new ArrayList<>(Arrays.stream(o.getClass().getDeclaredFields()).toList());
        fields.addAll(Arrays.stream(o.getClass().getSuperclass().getDeclaredFields()).toList());

        for(Field f : fields) {
            if(f.isAnnotationPresent(NullableWarning.class)) {
                f.setAccessible(true);
                try {
                    if (f.get(o) == null) {
                        System.out.println("Variable [" + f.getName() + "] is null in [" + o.getClass() + "]!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
