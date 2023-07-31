package com.ecommerceservice.application.controller;

public abstract class BaseController {

    // ------------------------------// PUBLIC API //-----------------------------//
    public static final String CREATE_USER = "/api/public/user/create";
    public static final String AUTHENTICATE_USER = "/api/public/user/authenticate";

    //-------------------------------// PRIVATE API //---------------------------//
    public static final String UPDATE_USER = "/api/user/update";
    public static final String CREATE_ITEM = "/api/item/create";
    public static final String UPDATE_ITEM = "/api/item/update";
    public static final String ITEM_LIST = "/api/item/list";
    public static final String DELETE_ITEM = "/api/item/{uuid}/delete";
    public static final String CREATE_CARD = "/api/card/create";
    public static final String UPDATE_CARD = "/api/card/update";
    public static final String CARD_LIST = "/api/card/list";
    public static final String DELETE_CARD = "/api/card/{uuid}/delete";
    public static final String CREATE_COUNTRY = "/api/country/create";
    public static final String UPDATE_COUNTRY = "/api/country/update";
    public static final String COUNTRY_LIST = "/api/public/country/list";
    public static final String DELETE_COUNTRY = "/api/country/{uuid}/delete";
    public static final String CREATE_ADDRESS = "/api/address/create";
    public static final String UPDATE_ADDRESS = "/api/address/update";
    public static final String ADDRESS_LIST = "/api/address/list";
    public static final String DELETE_ADDRESS = "/api/address/{uuid}/delete";


}
