package DataLayer.Mappers;

import BuisnnesLayer.Category;
import BuisnnesLayer.Item;
import DataLayer.PersistanceObjects.ItemPer;

import java.sql.*;
import java.util.LinkedList;


public class DataController {
    private static DataController instance = null;
    private ItemMapper itemMapper;
    private CategoriesMapper CategoriesMapper;

    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }

    private DataController() {
        itemMapper = new ItemMapper();
        CategoriesMapper= new CategoriesMapper();
    }

    //TODO: return null if item does not exist
    //Item Actions:
    //If we want to retrive an item which was not in the business
    public Item getItem(int product_id, int item_id) {
        Item ip = itemMapper.getItem(product_id, item_id);
        return ip;
    }
    //If we want to make entire new record of an item
    public boolean insertItem(Item obj) {
        return itemMapper.insertItem(obj);
    }

    public boolean update(Item obj) {
        return itemMapper.update(obj);
    }

    public boolean delete(Item obj) {
        return itemMapper.delete(obj);
    }

    public Category getCategory(String cat_name) {
        Category cat = CategoriesMapper.getCategory(cat_name);
        return cat;
    }
    public boolean insertCategory(Category category) {
        return CategoriesMapper.insertCategory(category);
    }
    public boolean update(Category obj) {
        return CategoriesMapper.update(obj);
    }

    public boolean delete(Category obj) {
        return CategoriesMapper.delete(obj);
    }
    public boolean setFather(Category cat,Category father_cat) {

        return CategoriesMapper.setFather(cat,father_cat);
    }
    public LinkedList<Category> loadAllCategoreis() {
     return  CategoriesMapper.loadAllCategories();
    }
}
