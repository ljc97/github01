#namespace("user")
    #sql("findUserList")
      select * from t_sys_user
      #for(x:cond)
        #(for.index == 0 ? "where" : "and") #(x.key)=#para(x.value)
      #end
    #end

    #sql("findUser")
      select * from t_sys_user
    #end
#end