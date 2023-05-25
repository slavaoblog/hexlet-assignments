package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String text;
    private List<Tag> list;
    public PairedTag(String name, Map<String, String> attributes, String text, List<Tag> list) {
        super(name, attributes);
        this.text = text;
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<" + super.name);
        for (String key : super.attributes.keySet()) {
            sb.append(" " + key + "=\"" + super.attributes.get(key) + "\"");
        }
        sb.append(">");
        for (Tag tag : list) {
            sb.append(tag.toString());
        }
        sb.append(text);
        sb.append("</" + super.name + ">");
        return sb.toString();
    }
}
// END
