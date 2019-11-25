package services.dto;

import com.avaje.ebean.Model;

public class ClienteExternoWebEntity extends Model {
    public long id;

    public long id_client;

    public String name = null;

    public String email = null;

    public String location = null;

    public String index_debt = null;

    public String index_billing = null;

    public String last_day_charged = null;

    public String msisdn;

    public long id_country;

    public long id_carrier;

    public String preference_type_content = null;

    public String ext_id;

    public long id_services;

    public long id_business;

    public long status;

    public String password;

    public long credit;

    public long credit_interaction;

    public long debit;

    public String last_billed;

    public long flag_rebilling;

    public long mt_count;

    public String last_mo;

    public String last_checked = null;

    public String ext_service_id = null;

    public String news_category = null;
}