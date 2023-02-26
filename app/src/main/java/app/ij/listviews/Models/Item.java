package app.ij.listviews.Models;
/*

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {

    private String author;
    private String name;
    private Integer task_id;

    private boolean delete;

    public Item() {}

    public Item(Integer task_id, String name, String author) {
        this.task_id = task_id;
        this.name = name;
        this.author = author;
        this.delete = false;
    }


    public int hashCode() {
        return Objects.hash(task_id, name, author);
    }

}


 */

import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {
    private String shoppingListId, shoppingListName, createdBy;
    @ServerTimestamp
    private Date date;

    private boolean delete;
    
    public Item() {}

    public Item(String shoppingListId, String shoppingListName, String createdBy) {
        this.shoppingListId = shoppingListId;
        this.shoppingListName = shoppingListName;
        this.createdBy = createdBy;
    }

    public String getShoppingListId() {
        return shoppingListId;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getDate() {
        return date;
    }
}