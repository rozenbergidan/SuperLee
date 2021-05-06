package DataLayer;

import BuisnnesLayer.Category;
import BuisnnesLayer.GeneralProduct;
import BuisnnesLayer.Item;
import BuisnnesLayer.SupplierBuissness.Supplier;
import DataLayer.PersistanceObjects.ItemPer;
import DataLayer.PersistanceObjects.PersistanceObj;

import java.util.HashMap;
import java.util.LinkedList;

public class IdentityMap {
    private static IdentityMap instance = null;
    private LinkedList<Item> itemList;
    private LinkedList<GeneralProduct> generalProductList;
    private LinkedList<Category> categoryList;
    private LinkedList<Supplier> suppliersList;

    public static IdentityMap getInstance() {
        if (instance == null){
            instance = new IdentityMap();
        }
        return instance;
    }

    private IdentityMap(){
        itemList = new LinkedList<>();
        categoryList = new LinkedList<>();
        suppliersList = new LinkedList<>();
        generalProductList = new LinkedList<>();
    }

    //add an item to the identityMap
    public void addItem(Item item) {
        itemList.add(item);
    }

    //TODO: need to add empty constructor for each created object
    //if this function return null - go to the db
    public Item getItem(int item_id, int gp_id) {
        Item output = null;
        for (Item item: itemList) {
            if(item.getItem_id()==item_id && item.getProduct_id() ==gp_id)
                output = item;
        }
        return output;
    }

    //TODO: make sure remove doesnt stop the for
    public boolean removeItem(int item_id, int gp_id){
        boolean output = false;
        for (Item item: itemList) {
            if(item.getItem_id()==item_id && item.getProduct_id() ==gp_id)
                output = itemList.remove(item);
        }
        return output;
    }

    public void addGeneralProduct(GeneralProduct product){
        generalProductList.add(product);
    }
    //TODO: need to add empty constructor for each created object
    //if this function return null - go to the db
    public GeneralProduct getGeneralProduct(int gp_id) {
        GeneralProduct output = null;
        for (GeneralProduct prod: generalProductList) {
            if(prod.getProduct_id() ==gp_id)
                output = prod;
        }
        return output;
    }

    //TODO: make sure remove doesnt stop the for
    public boolean removeGeneralProd(int gp_id){
        boolean output=false;
        for (GeneralProduct prod: generalProductList) {
            if(prod.getProduct_id() ==gp_id)
                output = generalProductList.remove(prod);
        }
        return output;
    }

}