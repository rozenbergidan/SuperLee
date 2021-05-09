package BuisnnesLayer;

import DataLayer.DataController;

import java.util.HashMap;
import java.util.List;

public class AgreementManager {
    private HashMap<Integer, IAgreement> SupplierAgreement; //- SupplierAgreement : hashmap<SupplierId : int ,agreement :Agreement>
    private int idProductCounter;
    private productManager productManager;

    public AgreementManager(productManager productManager) {
        // ProductsInTheSystem=new LinkedList<>();
        SupplierAgreement = new HashMap<>();
        idProductCounter = 0;
        this.productManager=productManager;
    }

    public void AddNewAgreement(IAgreement agreement) {
        SupplierAgreement.put(agreement.getSupplierID(), agreement);
        add_to_data_agreement((Agreement)agreement);

    }

    public HashMap<Integer, HashMap<Integer, Integer>> get_cheapest_supplier(HashMap<GeneralProduct, Integer> lackMap) {
        HashMap<Integer, HashMap<Integer, Integer>> cheapest_supplier_products_by_quantity = new HashMap<>(new HashMap<>());
        ProductSupplier productSupplier=null;
        double cheapest_supplier=-1;
        int supllierid=-1;
        int CatalogID=-1;
        int qountity=-1;
        double old_cheapest_supplier=-1;

        for (GeneralProduct generalProduct : lackMap.keySet()) {
            HashMap<Integer,ProductSupplier> HashOfSupplierProducts=generalProduct.getHashOfSupplierProducts();
            cheapest_supplier=-1;

            for (Integer SupplierId : SupplierAgreement.keySet()) {
                 old_cheapest_supplier=cheapest_supplier;

                for (Integer i : HashOfSupplierProducts.keySet()){
                     productSupplier=(HashOfSupplierProducts.get(i));
                if (cheapest_supplier==-1){
                    cheapest_supplier=(GetAgreement(SupplierId)).Calculate_cost(productSupplier,lackMap.get(generalProduct));
                }
                else
                    cheapest_supplier=Math.min(cheapest_supplier,(GetAgreement(SupplierId)).Calculate_cost(productSupplier,lackMap.get(generalProduct)));
                }
                if (old_cheapest_supplier!=cheapest_supplier)
                {
                    supllierid=SupplierId;
                    CatalogID=productSupplier.getCatalogID();
                    qountity=lackMap.get(generalProduct);
                }
            }

            if (cheapest_supplier!=-1){
                if (!cheapest_supplier_products_by_quantity.containsKey(supllierid))
                {
                    cheapest_supplier_products_by_quantity.put(supllierid,new HashMap<>());
                }
                (cheapest_supplier_products_by_quantity.get(supllierid)).put(CatalogID,qountity);

            }

        }
        return cheapest_supplier_products_by_quantity;
    }


    public void AddNewAgreement(int id, DeliveryMode deliveryMode, List<Integer> daysOfDelivery, int NumOfDaysFromDelivery) {
        idProductCounter++;
        Agreement agreement=new Agreement(id,deliveryMode,daysOfDelivery,NumOfDaysFromDelivery);
        SupplierAgreement.put(id,agreement);
        add_to_data_agreement(agreement);
    }

    //ok Data
    public IAgreement GetAgreement(int SupplierId) {
        if(!isSupplierExist(SupplierId)){
            IAgreement AG=get_froM_data_Agreement(SupplierId);
            if(AG==null)
                throw new IllegalArgumentException("the Supplier IS nOT exsist");
            return  AG;
        }
        return SupplierAgreement.get(SupplierId);

    }

    //ok data
    public IAgreement RemoveAgreement(int SupplierId) {
     Agreement agreement= (Agreement)GetAgreement(SupplierId);
        SupplierAgreement.remove(SupplierId);
        remove_from_data_agreement(agreement);
     return agreement;

    }



    //task 2 shinuyim hear!!!!
    //String product_name, Integer product_id, String manufacturer_name, Integer min_amount, Double cost_price, String cat, Double selling_price
    public void AddProduct(int SupplierId,double Price, int CatalogID,String product_name,String manufacture_name,String cat,int product_id,boolean existProductId) throws Exception {
        GeneralProduct gp;
        if(!isSupplierExist(SupplierId)){
            throw new IllegalArgumentException("the Supplier IS not exist");
        }
        if(GetAgreement(SupplierId).isProductExist(CatalogID)){
            throw new IllegalArgumentException("the Product already exist");
        }
        if(!productManager.check_category_exist(cat)){
            productManager.addCategory(cat);
        }
        // the product id is already exis
        if(existProductId){
           if (!productManager.check_product_id_exist(product_id)){
                throw new IllegalArgumentException("the genaral product not exsist");
            }
            //TODO with daniels code there is a problem when adding an item which exist in the stock to a supplier - another catagory is created
            ProductSupplier productSupplier=(GetAgreement(SupplierId)).AddPrudact(Price,CatalogID,product_id,productManager.get_product(product_id).getProduct_name());
            productManager.AddProductSupplierToProductGeneral(productSupplier,product_id);
        }
        else{
            ProductSupplier productSupplier=(GetAgreement(SupplierId)).AddPrudact(Price,CatalogID,idProductCounter,product_name);
            productManager.addProduct(product_name,idProductCounter,manufacture_name,-1,cat,-1.0,productSupplier);
        idProductCounter++;
        }
//        gp.add_t
   }

    public void  removeProduct(int SupplierId,int CatalogID) throws Exception {
        productManager.RemoveSupllierProductFromGeneralProduct(GetAgreement(SupplierId).GetPrudact(CatalogID).getId(),CatalogID);
        GeneralProduct generalProduct=productManager.get_product(GetAgreement(SupplierId).GetPrudact(CatalogID).getId());
        if(generalProduct.isSupplierProducHashEmpty()){
            productManager.remove_product(GetAgreement(SupplierId).GetPrudact(CatalogID).getId());
        }
        GetAgreement(SupplierId).RemovePrudact(CatalogID);
    }


    public boolean isSupplierExist(Integer suplplierId){
        IAgreement agreement=GetAgreement(suplplierId);
        if(agreement!=null){
            return true;
        }
        return false;
   }


/////////=========================================================DATA====================================================================================

    private void add_to_data_agreement(Agreement agreement) {
        IdentityMap im = IdentityMap.getInstance();
        DataController dc = DataController.getInstance();
        if (!dc.insertAgreement(agreement)) {
            System.out.println("failed to update new Agreement to the database");
        }
        im.addAgreement(agreement);
    }


    private  void remove_from_data_agreement(Agreement agreement){
        IdentityMap im = IdentityMap.getInstance();
        DataController dc = DataController.getInstance();
        if(!dc.deleteAreementFronData(agreement)){
            System.out.println("failed to update new Agreement to the database");
        }
    }


    private Agreement get_froM_data_Agreement(int Supid){
        IdentityMap im = IdentityMap.getInstance();
        DataController dc = DataController.getInstance();

        Agreement agreement=im.getAgreement(Supid);
        if(agreement!=null){
            return agreement;
        }
        else {
            agreement=dc.getAgreement(Supid);
            if(agreement==null){
                throw new IllegalArgumentException("canot get agrement from the data");
            }
            return agreement;
        }
    }
}
 //   public void ReplaceAgreement(int SupplierId,IAgreement agreement){
   //     SupplierAgreement.put(SupplierId,agreement);
  //  }

//    public void AddProduct(Product product){
//        if (!ProductsInTheSystem.contains(product)) {
//            ProductsInTheSystem.add(product);
//            idProductCounter++;
//        }
//    }
//    public void RemoveProduct(Product product){
//        if (ProductsInTheSystem.contains(product)) {
//            ProductsInTheSystem.remove(product);
//        }
//        else throw new IllegalArgumentException("the product is not exist in this System");
//    }
//}
