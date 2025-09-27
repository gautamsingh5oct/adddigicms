CREATE USER 'adddigicms'@'localhost' IDENTIFIED BY 'adddigicms56!';
GRANT ALL PRIVILEGES ON addigicharging.* TO 'adddigicms'@'localhost' IDENTIFIED BY 'adddigicms56!';
FLUSH PRIVILEGES;
COMMIT;


insert into tb_portal select null,portal_desc,portal_name,portal_desc2,portal_name2,153,portal_banner_text,portal_banner_text2,portal_banner,portal_banner2,now(),is_demo_portal,1,'http://www.gamzfun.com:8080/addigicharging/cnt/cmp?adid=1&cmpid=292&token=test','[50]',categories,portal_view,content_view,myaccount_view,msisdn_missing_view,'Dot Indosat IOH',term_condition_url,check_sub_status from tb_portal where id=38;



