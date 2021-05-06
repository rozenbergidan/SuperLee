package BuisnnesLayer;

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
                    cheapest_supplier=(SupplierAgreement.get(SupplierId)).Calculate_cost(productSupplier,lackMap.get(generalProduct));
                }
                else
                    cheapest_supplier=Math.min(cheapest_supplier,(SupplierAgreement.get(SupplierId)).Calculate_cost(productSupplier,lackMap.get(generalProduct)));
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
        SupplierAgreement.put(id,new Agreement(id,deliveryMode,daysOfDelivery,NumOfDaysFromDelivery));
    }

    public IAgreement GetAgreement(int SupplierId) {
        if(!isSupplierExist(SupplierId)){
            throw new IllegalArgumentException("the Supplier IS nOT exsist");
        }
        return SupplierAgreement.get(SupplierId);

    }
    public IAgreement RemoveAgreement(int SupplierId) {

        return SupplierAgreement.remove(SupplierId);

    }

    //task 2 shinuyim hear!!!!
    //String product_name, Integer product_id, String manufacturer_name, Integer min_amount, Double cost_price, String cat, Double selling_price
    public void AddProduct(int SupplierId,double Price, int CatalogID,String product_name,String manufacture_name,String cat,int product_id,boolean existProductId) throws Exception {
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
           if (!productManager.check_product_exist(product_name)){
                throw new IllegalArgumentException("the genaral product not exsist");
            }
            ProductSupplier productSupplier=(SupplierAgreement.get(SupplierId)).AddPrudact(Price,CatalogID,product_id,product_name);
            productManager.addProduct(product_name,product_id,manufacture_name,-1,cat,-1.0,productSupplier);
        }
        else{
            ProductSupplier productSupplier=(SupplierAgreement.get(SupplierId)).AddPrudact(Price,CatalogID,idProductCounter,product_name);
            productManager.addProduct(product_name,idProductCounter,manufacture_name,-1,cat,-1.0,productSupplier);
        idProductCounter++;}
   }

    public void  removeProduct(int SupplierId,int CatalogID){
        productManager.RemoveSupllierProductFromGeneralProduct(GetAgreement(SupplierId).GetPrudact(CatalogID));
        GetAgreement(SupplierId).RemovePrudact(CatalogID);
    }


    public boolean isSupplierExist(Integer suplplierId){
        return SupplierAgreement.containsKey(suplplierId);
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