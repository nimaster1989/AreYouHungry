package comp3350.Group2.areyouhungry.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample food_name for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
        addItem(new DummyItem("001","fish","www.google1.com"));
        addItem(new DummyItem("002","burger","www.google2.com"));
        addItem(new DummyItem("003","bruiito","www.google3.com"));
        addItem(new DummyItem("004","salad","www.google4.com"));
        addItem(new DummyItem("005","rice","www.google5.com"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore detail information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of food_name.
     */
    public static class DummyItem {
        public final String id;
        public final String food_name;
        public final String recipeLink;

        public DummyItem(String id, String food_name, String recipeLink) {
            this.id = id;
            this.food_name = food_name;
            this.recipeLink = recipeLink;
        }

        @Override
        public String toString() {
            return food_name;
        }
    }
}