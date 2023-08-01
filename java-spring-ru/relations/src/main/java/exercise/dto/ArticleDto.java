package exercise.dto;

import exercise.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// BEGIN
public class ArticleDto {
    private String name;
    private String body;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
// END
