'use strict';

/**
 * @ngdoc function
 * @name psportApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the psportApp
 */
angular.module('psportApp')
  .controller('MainCtrl', ["$scope", function ($scope)  {
  	

  	function init()
  	{
  		$.backstretch("images/bckgrnd.jpg");
  		focusAndClear("input[type=number]"); 
	    $("#pin").hide();
	    $("#inputpin").hide();
	    $("#returndiv").hide();  
	    //$("#infobox").hide();  
	    initial = 0;
  	}init();

  	var initial;
	var celular;

	$scope.clicked = function()
	{
	   var clear;
	    if (initial === 0){
	        celular = $("input[type=number]").val();
	        if (celular.length === 0) {
	        	$("#infobox").html('Pronto recibirás un mensaje de texto para suscribirte a +Sports');   
	            //alert("Ingrese su número para suscribirse");    
	        } else {
	            if(!celular.startsWith("507")) {
	                celular = "507" + celular;    
	            }            
	            genericGenPin(celular);            
	            clear = "input[type=number]";           
	        }    
	    } else {
	        var pin = $("#inputpin").val();
	        if(pin.length === 4){
	            var promiseCheck = new Promise(function(resolve, reject){
	                callCheckPin(celular, pin, resolve, reject);                                       
	            });
	            promiseCheck.then(function(res){
	                if(res){
	                    goPutPhone();
	                }
	            }, function(error){
	                console.error("Error en la llamada a callCheckPin ", error);
	            });                
	        } else {
	        	$("#infobox").html('El numero de caracteres del pin debe ser 4');
	            //alert("El numero de caracteres del pin debe ser 4");
	        }
	        clear = "#inputpin";
	    }   
	    focusAndClear(clear);
	}

	$("#buttonss").on("click", function() {
		event.stopPropagation();
		event.preventDefault();        
		//alert('lol');
	    var clear;
	    if (initial === 0){
	        celular = $("input[type=number]").val();
	        if (celular.length === 0) {
	            alert("Ingrese su número para suscribirse");    
	        } else {
	            if(!celular.startsWith("507")) {
	                celular = "507" + celular;    
	            }            
	            genericGenPin(celular);            
	            clear = "input[type=number]";           
	        }    
	    } else {
	        var pin = $("#inputpin").val();
	        if(pin.length === 4){
	            var promiseCheck = new Promise(function(resolve, reject){
	                callCheckPin(celular, pin, resolve, reject);                                       
	            });
	            promiseCheck.then(function(res){
	                if(res){
	                    goPutPhone();
	                }
	            }, function(error){
	                console.error("Error en la llamada a callCheckPin ", error);
	            });                
	        } else {
	            alert("El numero de caracteres del pin debe ser 4");
	        }
	        clear = "#inputpin";
	    }   
	    focusAndClear(clear);
	});

	$scope.return = function()
	{
		event.preventDefault();
		$("#infobox").html('');
		 if(initial === 1){
	        goPutPhone();        
	    }
	}

	$("#returnss").on("click", function() {
	    if(initial === 1){
	        goPutPhone();        
	    }
	});

	function goPutPhone(){
	    init();
	    $("#cel").show();
	    $("input[type=number]").show();
	    focusAndClear("input[type=number]");
	}


	$scope.resent = function()
	{
		event.preventDefault();
		$("#infobox").html('');
		try {
	        genericGenPin(celular);    
	    } catch (error){
	        console.error("Error al activar el Reenviar pin ", error);
	    }    
	}

	$("#resentss").on("click", function(){
	    try {
	        genericGenPin(celular);    
	    } catch (error){
	        console.error("Error al activar el Reenviar pin ", error);
	    }    
	});

	function showAndHide(){        
	    $("#cel").hide();
	    $("input[type=number]").hide();
	    $("#pin").show();
	    $("#inputpin").show();
	    $("#returndiv").show();  
	    //$("#infobox").html('Pronto recibirás un mensaje de texto para suscribirte a +Sports');   
	}

	function genericGenPin(cel){    
	    var promise = new Promise(function(resolve, reject){
	        callGenPin(cel, resolve, reject);        
	    });     
	    var promisex = promise.then(function(res){
	        if(res){
	            initial = 1;            
	            showAndHide(); 
	        }
	    }, function(error){
	        console.error("Error en la llamada callGenPin ", error);
	    });    
	}

	function callGenPin(cel, resolve, reject){        
	    $.ajax({
	        type: 'POST',
	        url: 'http://plussports.hecticus.com/sportsapi/v1/clients/generatepin/'+cel,
	        //url: 'http://localhost:9010/sportsapi/v1/clients/generatepin/'+cel,        
	        crossDomain: true,
	        dataType: 'json'        
	    })
	        .done(function(data) {
	            console.log(data); 
	            if (data['error'] === 0) {
	                console.log("isGenerated " + data['response'].isGen);
	                if(data['response'].isGen){
	                	$("#infobox").html('Pronto recibirás un mensaje de texto para suscribirte a +Sports');   
	                    //alert('Pronto recibirás un mensaje de texto para suscribirte a +Sports'); 
	                    resolve(true);
	                } else {
	                	$("#infobox").html('Usted esta ya esta suscrito al servicio de +Sports');
	                    //alert('Usted esta ya esta suscrito al servicio de +Sports');
	                    resolve(false);
	                }                
	            } else {
	            	$("#infobox").html('Problema en la suscripción. Intente de nuevo mas tarde');
	                //alert('Problema en la suscripción. Intente de nuevo mas tarde');  
	                reject(false);
	            }            
	        }).fail(function(error){
	            console.log('Fail '+JSON.stringify(error));
	            alert('Problemas en la conexión. Intente de nuevo mas tarde');    
	            reject(false);
	        });
	}

	function callCheckPin(cel, pin, resolve, reject){        
	    $.ajax({
	        type: 'POST',
	        url: 'http://plussports.hecticus.com/sportsapi/v1/clients/checkpin/'+cel+'/'+pin, 
	        //url: 'http://localhost:9010/sportsapi/v1/clients/checkpin/'+cel+'/'+pin, 
	        crossDomain: true,
	        dataType: 'json'        
	    })
	        .done(function(data) {
	            console.log(data); 
	            if (data['error'] === 0) {
	                console.log("isConfirmed " + data['response'].isCheck);
	                if(data['response'].isCheck){
	                	$("#infobox").html('Suscripción registrada exitasomente');
	                    //alert('Suscripción registrada exitasomente');
	                    resolve(true);
	                } else {
	                	$("#infobox").html('Suscripcion invalida. Verifique de nuevo su PIN');
	                    //alert('Suscripcion invalida. Verifique de nuevo su PIN');
	                    resolve(false);
	                }               
	            } else {
	            	$("#infobox").html('Problema en la suscripción. Intente de nuevo mas tarde');
	                //alert('Problema en la suscripción. Intente de nuevo mas tarde');  
	                reject(false);
	            }
	        }).fail(function(error){
	            console.log('Fail '+JSON.stringify(error));
	            $("#infobox").html('Problemas en la conexión. Intente de nuevo mas tarde');
	            //alert('Problemas en la conexión. Intente de nuevo mas tarde');            
	            reject(false);
	        });
	}

	function focusAndClear(id){
	    $(id).focus().val("");    
	}

	$("input[type=number]").on("mousewheel keydown", function(e){ 
	    switch(e.type){
	        case 'mousewheel': $(this).blur();break;
	        case 'keydown': 
	            if(e.which == 38 || e.which == 40){
	                e.preventDefault();                
	            }
	            break;
	    }    
	});

	$("#play").on("click", function() {
	    window.location= "https://play.google.com/store/apps/details?id=com.hecticus.massports";
	});


    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  }]);
