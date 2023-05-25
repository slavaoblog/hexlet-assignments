package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<" + super.name);
        for (String key : super.attributes.keySet()) {
            sb.append(" " + key + "=\"" + super.attributes.get(key) + "\"");
        }
        sb.append(">");
        return sb.toString();
    }
}
// END
