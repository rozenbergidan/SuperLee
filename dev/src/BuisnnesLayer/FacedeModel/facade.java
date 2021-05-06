package BuisnnesLayer.FacedeModel;

import BuisnnesLayer.*;
import BuisnnesLayer.Controlls.Reports_Controller;
import BuisnnesLayer.Controlls.Sales_Controller;
import BuisnnesLayer.Controlls.Stock_Controller;
import BuisnnesLayer.FacedeModel.Objects.*;
import BuisnnesLayer.OrderBuissness.Order;
import BuisnnesLayer.Reports.Report;
import BuisnnesLayer.Sales.Sale;
import BuisnnesLayer.SupplierBuissness.Contact;
import BuisnnesLayer.SupplierBuissness.ISupplier;
import BuisnnesLayer.FacedeModel.inventModel;
//import com.sun.source.tree.NewArrayTree;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class facade {
    private static facade single_instance = null;
    private inventModel inventModel;
    private SupModel supModel;
    private productManager ProductManager;
//    private Stock_Controller stock_controller;
//    private Sales_Controller sales_controller;
//    private Reports_Controller reports_controller;
//for invent
private facade(){
    ProductManager=new productManager();
    inventModel=new inventModel(ProductManager);
    supModel= new SupModel(ProductManager);

}
    public static facade getInstance()
    {
        if (single_instance == null)
            single_instance = new facade();
        return single_instance;
    }

    public Response create_order_Due_to_lack() {

        return supModel.create_order_Due_to_lack((inventModel.getStockC()).get_missing_General_products_with_amounts());

    }
    ////////////////////////////////////////
    public Stock_Controller getStockC() {
        return inventModel.getStockC();
    }
    public static Date getDate(String date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(date);
    }

    public static String getDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
////////////////////////////////
    public Sales_Controller getSalesC() {
        return inventModel.getSalesC();
    }
    public Response create_category(String cat_name) {
        return inventModel.create_category(cat_name);
    }


    public Response remove_category_from_stock(String cat_name) {
        return inventModel.remove_category_from_stock(cat_name);
    }


    public Response set_father(String cat_name, String cat_father_name) {
       return inventModel.set_father(cat_name,cat_father_name);
    }


    public Response get_product_info(Integer product_id) {
        return inventModel.get_product_info(product_id);
    }


    public Response clear_stock_defected() {
       return inventModel.clear_stock_defected();
    }




    public Response update_location(Integer item_id, Integer product_id, String new_location) {
        return inventModel.update_location(item_id,product_id,new_location);

    }

    public Response add_product_items(Integer product_id, Integer quantity, String location, String supplied_date, String creation_date, String expiration_date) {
        return inventModel.add_product_items(product_id,quantity,location,supplied_date,creation_date,expiration_date);

    }
    //needed to chang to Response
    public void addProduct(String product_name, Integer product_id, String manufacturer_name, Integer min_amount,  String cat, Double selling_price,ProductSupplier productSupplier) throws Exception {
        ProductManager.addProduct(product_name,product_id,  manufacturer_name,  min_amount,   cat,  selling_price, productSupplier,cat);

}
    public Response remove_product_items(Integer product_id, Integer item_id) {
        return inventModel.remove_product_items(product_id,item_id);

    }

    public Response update_product_min_amount(Integer product_id, Integer min_amount) {
        return inventModel.update_product_min_amount(product_id,min_amount);
    }

    public Response set_item_defected(Integer product_id, Integer item_id) {
        return inventModel.set_item_defected(product_id,item_id);

    }

    public Response show_all_products() {
     return inventModel.show_all_products();
    }


    public Response show_all_categories() {
        return inventModel.show_all_categories();
    }

    public Response update_product_selling_price(int product_id, double price) {
        return inventModel.update_product_selling_price(product_id,price);

    }

    public Response get_product_items(Integer product_id) {
        return inventModel.get_product_items((product_id));

    }

    public Response add_sale_by_category(Double discount, String desc, String start_date, String end_date, LinkedList<String> affected_category) {
        return inventModel.add_sale_by_category(discount,desc,start_date,end_date,affected_category);
    }


    public Response addSaleByProduct(Double discount, String desc, String start_date, String end_date, LinkedList<String> affected_Products) {
     return inventModel.addSaleByProduct(discount,desc,start_date,end_date,affected_Products);
    }

    public Response updateSales() {
        return inventModel.updateSales();
    }


    public Response removeSale(int sale_id) {
        return inventModel.removeSale(sale_id);
    }

    public Response all_sales() {
      return inventModel.all_sales();
    }


    public Response get_sale_by_category_name(String cat_name) {
        return inventModel.get_sale_by_category_name(cat_name);
    }


    public Response get_sale_by_product_name(String product) {
     return inventModel.get_sale_by_product_name(product);
    }

    public Response update_sale_description(int sale_id, String desc) {
      return  inventModel.update_sale_description(sale_id,desc);
    }


    public Response update_sale_discount(int sale_id, double disc) {
        return inventModel.update_sale_discount(sale_id,disc);
    }


    public Response createReport(String subject, String timeRange, LinkedList<String> categories) {
        return inventModel.createReport(subject,timeRange,categories);
    }

    public Response get_report_by_id(Integer report_id) {
     return inventModel.get_report_by_id(report_id);
    }

    public Response get_reports_id(String subject, String date) {
   return inventModel.get_reports_id(subject,date);
    }

    public Response show_all_reports() {
      return inventModel.show_all_reports();
    }



    //supp model

    public Response addNewSupplier(int id, String name, String bankAccount, paymentMethods paymentMethods, DeliveryMode deliveryMode, List<Integer> daysOfDelivery, int NumOfDaysFromDelivery, String contactName, String contactEmail, String phoneNumber)
    {
     return supModel.addNewSupplier(id,name,bankAccount,paymentMethods,deliveryMode,daysOfDelivery,NumOfDaysFromDelivery,contactName,contactEmail,phoneNumber);

    }

    public Response removeSupplier(int SupId) {
        return supModel.removeSupplier(SupId);
    }


    public Response setSupplierPayment(paymentMethods paymentMethods, int SupplierId) {
        return supModel.setSupplierPayment(paymentMethods,SupplierId);
    }
    //SetDeliveryMode()
    public Response SetDeliveryMode(int SupId,DeliveryMode deliveryMods, List<Integer> daysOfDelivery,int numOfDaysFromOrder) {
    return supModel.SetDeliveryMode(SupId,deliveryMods,daysOfDelivery,numOfDaysFromOrder);
    }

    public Response addNewProductToAgreement(int SupplierId,double Price, int CatalogID, String manfucator, String name,String category,int pid, boolean isexist) {
    return supModel.addNewProductToAgreement(SupplierId,Price,CatalogID,manfucator,name,category,pid,isexist);
    }

    public Response removeProductFromSupplier(int SupId, int CatalogID) {
        return supModel.removeProductFromSupplier(SupId,CatalogID);
    }

    //            facade.removeProductFromOrder(id,catalogId);
    public Response RemovePrudactFromOrders(int SupId, int CatalogID) {
        return supModel.RemovePrudactFromOrders(SupId,CatalogID);

    }


    public Response setProductPrice(int SupId, int CatalogID, double price) {
        return supModel.setProductPrice(SupId,CatalogID,price);

    }

    public Response addNewDiscountByQuantitiyToProduct(int SupId, int CatalogID, int Quantitiy, double newPrice) {
        return supModel.addNewDiscountByQuantitiyToProduct(SupId,CatalogID,Quantitiy,newPrice);

    }

    public Response setExtraDiscountToSupplier(int SupId, int ExtraDiscount) {
    return supModel.setExtraDiscountToSupplier(SupId,ExtraDiscount);
    }

    public Response addNewContactMember(int SupId, String contactName, String contactEmail, String phoneNumber) {
    return supModel.addNewContactMember(SupId,contactName,contactEmail,phoneNumber);
    }

    public ResponseT getSupplier(int SupId) {
    return supModel.getSupplier(SupId);
    }

    public ResponseT getAllSuppliers() {
    return supModel.getAllSuppliers();
    }

    public ResponseT getAgreement(int SupId) {
    return supModel.getAgreement(SupId);
    }

    public Response RemoveContact(int SupId,int ContactID) {
        return supModel.RemoveContact(SupId,ContactID);
    }

    public Response addNewOrder(int SupId,HashMap<Integer,Integer> productQuantity,boolean isConstant) {
    return supModel.addNewOrder(SupId,productQuantity,isConstant);
    }

    public Response addProductToOrder(int SupId,int OrderId, int CatalogID, int quantity) {
        return supModel.addProductToOrder(SupId,OrderId,CatalogID,quantity);
    }

    public Response changeProductQuantityFromOrder(int SupId,int OrderId, int CatalogID, int quantity) {
        return supModel.changeProductQuantityFromOrder(SupId,OrderId,CatalogID,quantity);
    }

    public Response RemovDiscountByQuantitiyToProduct(int SupId, int CatalogID, int Quantitiy) {
        return supModel.RemovDiscountByQuantitiyToProduct(SupId,CatalogID,Quantitiy);
    }

    public Response removeOrder(int OrderID) {
        return supModel.removeOrder(OrderID);

    }

    public ResponseT getOrder(int OrderID) {
    return supModel.getOrder(OrderID);
    }

    public ResponseT getAllOrders() {
     return supModel.getAllOrders();
    }








}
