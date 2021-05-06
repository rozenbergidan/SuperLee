package DataLayer.Mappers;

import BuisnnesLayer.GeneralProduct;
import BuisnnesLayer.Item;


public class DataController {
    private static DataController instance = null;
    private ItemMapper itemMapper;
    private GeneralProductMapper generalProductMapper;

    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }

    private DataController() {
        itemMapper = new ItemMapper();
        generalProductMapper = new GeneralProductMapper();
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

    //GeneralProduct Actions:
    //If we want to retrive an product which was not in the business
    public GeneralProduct getGP(int product_id) {
        GeneralProduct gp = generalProductMapper.getGeneralProduct(product_id);
        //TODO: need to add here itemsList and supplierProductList
        return gp;
    }
    //If we want to make entire new record of an item
    public boolean insertGP(GeneralProduct obj) {
        return generalProductMapper.insertProduct(obj);
    }

    public boolean update(GeneralProduct obj) {
        return generalProductMapper.update(obj);
    }

    public boolean delete(GeneralProduct obj) {
        return generalProductMapper.delete(obj);
    }

}