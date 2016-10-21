'use strict';
var our_project_base_url = "http://demo.tjuwork.win:9000/api" //远程服务器


var getUserPromise = {
    
    user: function(){
        return $.getJSON(our_project_base_url+"/user").then(function(data) {
          return data.name;
        });
    }
}


var getProblemsPromise = {
    
    getProblems: function(toGet="/1/1/2/100/1000"){
        
        return $.ajax({
            url: our_project_base_url+"/newProblems/"+toGet
        })
        .done(function(problems, status, jqxhr) {
            var problems_store = []
            for(let p of problems){
               //alert(p.definition + "\n" + p.answer);
                problems_store.push(p)
//                console.log("in,",problems_store)
            }
            console.log(jqxhr);
            return problems_store;
        })
        .fail(function(){
            alert("We failed");
        })
    }
    
    
}