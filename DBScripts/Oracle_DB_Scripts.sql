create user olbs identified by olbs

grant resource, connect to olbs

CREATE SEQUENCE CREDITCARDAPPLICATIONSEQ
  MINVALUE 1
  MAXVALUE 9999999999999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  
  CREATE SEQUENCE CUSTOMERSEQ
  MINVALUE 1
  MAXVALUE 9999999999999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;

http://localhost:8088/apex/f?p=4550:1:2672676752988328

CREATE TABLE customer_info (
    custid VARCHAR(6),
    fname VARCHAR(30),
    mname VARCHAR(30),
    ltname VARCHAR(30),
    city VARCHAR(15),
    mobileno VARCHAR(10),
    occupation VARCHAR(10),
    dob DATE,
    email VARCHAR(50),
    CONSTRANUMBER customer_custid_pk PRIMARY KEY (custid)
);

CREATE TABLE branch (
    bid VARCHAR(6),
    bname VARCHAR(30),
    bcity VARCHAR(30),
    CONSTRANUMBER branch_bid_pk PRIMARY KEY (bid)
);

CREATE TABLE account_info
   (
      acnumber VARCHAR(6),
      custid  VARCHAR(6),
      bid VARCHAR(6),
      opening_balance NUMBER(7),
      aod DATE,
      atypeid VARCHAR(10),
      astatus VARCHAR(10),
      CONSTRANUMBER account_acnumber_pk PRIMARY KEY(acnumber),
      CONSTRANUMBER account_custid_fk FOREIGN KEY(custid) REFERENCES customer_info(custid),
      CONSTRANUMBER account_bid_fk FOREIGN KEY(bid) REFERENCES branch(bid),
      CONSTRANUMBER account_typeid_fk FOREIGN KEY(atypeid) REFERENCES account_types (atypeid)
    );

CREATE TABLE account_types (
    atypeid VARCHAR(6),
    atypename VARCHAR(30),
    CONSTRANUMBER account_typeid_pk PRIMARY KEY (atypeid)
);
CREATE TABLE txn_schedule
   (
    scid VARCHAR(6),
    from_actnumber VARCHAR(6),
    to_actnumber VARCHAR(6),
    scamount NUMBER(7),
    scdate DATE,
    scremarks VARCHAR(30),
    CONSTRANUMBER txn_schedule_scid_pk PRIMARY KEY(scid),
CONSTRANUMBER txn_scid_fromacct_fk FOREIGN KEY(from_actnumber) REFERENCES account_info(acnumber), 
CONSTRANUMBER txn_scid_toacct_fk FOREIGN KEY(to_actnumber) REFERENCES account_info(acnumber)
   );
CREATE TABLE transaction_info (
    tnumber VARCHAR(6),
    acnumber VARCHAR(6),
    dot DATE,
    medium_of_transaction VARCHAR(20),
    transaction_type VARCHAR(20),
    transaction_amount NUMBER(7),
    CONSTRANUMBER trandetails_tnumber_pk PRIMARY KEY (tnumber),
    CONSTRANUMBER trandetails_acnumber_fk FOREIGN KEY (acnumber)
        REFERENCES account_info (acnumber)
);

CREATE TABLE loan (
    lnumber VARCHAR(6),
    custid VARCHAR(6),
    bid VARCHAR(6),
    loan_amount NUMBER(7),
    ltenure VARCHAR(6),
    lemi NUMBER(7),
    CONSTRANUMBER loan_customer_custid_bid_pk PRIMARY KEY (custid , bid),
    CONSTRANUMBER loan_custid_fk FOREIGN KEY (custid)
        REFERENCES customer_info (custid),
    CONSTRANUMBER loan_bid_fk FOREIGN KEY (bid)
        REFERENCES branch (bid)
);

CREATE TABLE credit_card (
    ccnumber VARCHAR(6),
    custid VARCHAR(6),
    bid VARCHAR(6),
    cc_amount_limit NUMBER(7),
    billing_sc VARCHAR(6),
    CONSTRANUMBER cc_ccnumber_pk PRIMARY KEY (ccnumber),
    CONSTRANUMBER cc_customer_custid_fk FOREIGN KEY (custid)
        REFERENCES customer_info (custid),
    CONSTRANUMBER cc_bid_fk FOREIGN KEY (bid)
        REFERENCES branch (bid)
);


