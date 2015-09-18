package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Config;
import org.apache.http.ParseException;

import javax.xml.ws.http.HTTPException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase para la interaccion con los Web Services
 * 
 * @author inaki
 */
public class WebServicesManager {
	
//	private static Logger logger = Logger.getLogger(WebServicesManager.class);

	URL url = null;

	/**
	 * Constructor para un WebServicesManager
	 * @param _url	direccion del web service a consultar
	 */
	public WebServicesManager(URL _url) {
		this.url = _url;
	}

	/**
	 * Funcion para obtener un contenido desde un web service
	 * 
	 * <p>
	 * 
	 * Levanta una conexion al WS y le pasa los parametros, luego revisa el estado de la conexion,
	 * si no es OK, lanza una excepcion. Si es OK procede a generar un string concatenando los campos
	 * de la respuesta. Luego convierte ese String a json y revisa si la respuesta trae el campo error, si error
	 * es diferente a 0 se manda una alarma. indiferentemente del error se retorna el json generado
	 * 
	 * 
	 * @param urlParameters		parametros a consultar al Web Service
	 * @return					Objeto que contiene un mapa con la respuesta del ws
	 * @throws javax.xml.ws.http.HTTPException
	 * @throws java.io.IOException
	 * @throws org.apache.http.ParseException
	 */
	public Object getContent(String urlParameters) throws HTTPException,IOException,ParseException {
		StringBuffer buffer = null;
		String line;
		int responseCode;
		HttpURLConnection connection = null;
		InputStream input = null;
		BufferedReader dataInput = null;
		int retryCounter = Config.getInt("ws-retry-count");
		int retryTime = Config.getInt("ws-retry-time");
		HTTPException exception = null;
		IOException ioexception = null;
		ParseException parseexception = null;
		ObjectNode jsonObj = null;
		try {
			while (retryCounter > 0) {
				try {
					connection = (HttpURLConnection) url.openConnection();
					//set post data
					connection.setRequestMethod("POST");
					connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					if (urlParameters != null) {
						connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
					}
					connection.setUseCaches(false);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					connection.setReadTimeout(60000);
					if (urlParameters != null) {
						//Send request
						DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
						wr.writeBytes(urlParameters);
						wr.flush();
						wr.close();
					}
					responseCode = connection.getResponseCode();
					if (responseCode != HttpURLConnection.HTTP_OK) {
						retryCounter--;
						Thread.sleep(retryTime);
						if(exception == null || exception.getStatusCode() != responseCode){
							exception = new HTTPException(responseCode);
						}
					} else {
						buffer = new StringBuffer();
						input = connection.getInputStream();
						dataInput = new BufferedReader(new InputStreamReader(input));
						while ((line = dataInput.readLine()) != null) {
							buffer.append(line);
							buffer.append("\r\n");
						}
						jsonObj = Utils.parseJsonString(buffer.toString());
					//	if (jsonObj instanceof Map) {
					//		Map map = (Map) jsonObj;
							if (jsonObj.has("error") && jsonObj.has("description")) {
								long errorCode = jsonObj.get("error").asLong();
								if (errorCode != 0) {
									retryCounter--;
									Thread.sleep(retryTime);
								} else {
									exception = null;
									ioexception = null;
									parseexception = null;
									return jsonObj;
								}
							} else if (jsonObj.has("status") && jsonObj.has("description")) {//ESTE FIX DEBE SER QUITADO LUEGO QUE SE STANDARIZEN TODOS LOS ws A REPONDER CON EL CAMPO ERROR
								long errorCode = jsonObj.get("status").asLong();
								if (errorCode != 0) {
									retryCounter--;
									Thread.sleep(retryTime);
								} else {
									exception = null;
									ioexception = null;
									parseexception = null;
									return jsonObj;
								}
							} else {
								throw new ParseException("UNEXPECTED_TOKEN " + jsonObj);
							}
					//	}
					}
				} catch (Exception ex) {
					retryCounter--;
					Thread.sleep(retryTime);
					if(retryCounter <= 0){
						throw ex;
					}
				}
			}
		} catch (Exception e) {
			if(e instanceof HTTPException){
				exception = (HTTPException)e;
				ioexception = null;
				parseexception = null;
			} else if(e instanceof IOException){
				exception = null;
				ioexception = (IOException)e;
				parseexception = null;
			} else if(e instanceof ParseException){
				exception = null;
				ioexception = null;
				parseexception = (ParseException)e;
			}else {
			//	deberia caer aqui?
			}
		} finally {
			if (dataInput != null) {try {dataInput.close();} catch (IOException ex) {}}
			if (input != null) {try {input.close();} catch (IOException ex) {}}
			if (connection != null) {connection.disconnect();}
			if (exception != null) {throw exception;}
			if (ioexception != null) {throw ioexception;}
			if (parseexception != null) {throw parseexception;}
		}

                if((jsonObj != null) && (jsonObj instanceof Map)){
                    return jsonObj;
                }
		//si buffer no es null entonces no era un json tampoco... este caso no deberia existir pero no se si sea necesario
		return buffer;
	}

	/**
	 * Funcion para obtener un contenido desde un web service indicando el método HTTP
	 *
	 * <p>
	 *
	 * Levanta una conexion al WS con el método http indicado, luego revisa el estado de la conexion,
	 * si no es OK, lanza una excepcion. Si es OK procede a generar un string concatenando los campos
	 * de la respuesta. Luego convierte ese String a json y revisa si la respuesta trae el campo error, si error
	 * es diferente a 0 se manda una alarma. indiferentemente del error se retorna el json generado
	 *
	 *
	 * @param httpMethod		metodo http para llamar el Web Service
	 * @return					Objeto que contiene un mapa con la respuesta del ws
	 * @throws javax.xml.ws.http.HTTPException
	 * @throws java.io.IOException
	 * @throws org.apache.http.ParseException
	 */
	@SuppressWarnings("rawtypes")
	public Object getContentFromPlay(String httpMethod) throws HTTPException,IOException,ParseException {
		StringBuffer buffer = null;
		String line;
		int responseCode;
		HttpURLConnection connection = null;
		InputStream input = null;
		BufferedReader dataInput = null;
		int retryCounter = Config.getInt("ws-retry-count");
		int retryTime = Config.getInt("ws-retry-time");
		HTTPException exception = null;
		IOException ioexception = null;
		ParseException parseexception = null;
		ObjectNode jsonObj = null;
		try {
			while (retryCounter > 0) {
				try {
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod(httpMethod);
					connection.setRequestProperty("Accept-Charset", "UTF-8");
					connection.setUseCaches(false);
					connection.setReadTimeout(60000);

					responseCode = connection.getResponseCode();
					if (responseCode != HttpURLConnection.HTTP_OK) {
						retryCounter--;
						Thread.sleep(retryTime);
						if(exception == null || exception.getStatusCode() != responseCode){
							exception = new HTTPException(responseCode);
						}
					} else {
						buffer = new StringBuffer();
						input = connection.getInputStream();
						dataInput = new BufferedReader(new InputStreamReader(input));
						while ((line = dataInput.readLine()) != null) {
							buffer.append(line);
							buffer.append("\r\n");
						}
						jsonObj = Utils.parseJsonString(buffer.toString());
						if (jsonObj instanceof Map) {
							Map map = (Map) jsonObj;
							if (map.containsKey("error") && map.containsKey("description")) {
								long errorCode = (Long) map.get("error");
								if (errorCode != 0) {
									retryCounter--;
									Thread.sleep(retryTime);
								} else {
									exception = null;
									ioexception = null;
									parseexception = null;
									return jsonObj;
								}
							} else if (map.containsKey("status") && map.containsKey("description")) {//ESTE FIX DEBE SER QUITADO LUEGO QUE SE STANDARIZEN TODOS LOS ws A REPONDER CON EL CAMPO ERROR
								long errorCode = (Long) map.get("status");
								if (errorCode != 0) {
									retryCounter--;
									Thread.sleep(retryTime);
								} else {
									exception = null;
									ioexception = null;
									parseexception = null;
									return jsonObj;
								}
							} else {
								throw new ParseException("UNEXPECTED TOKEN " + jsonObj);
							}
						}
					}
				} catch (Exception ex) {
					retryCounter--;
					Thread.sleep(retryTime);
					if(retryCounter <= 0){
						throw ex;
					}
				}
			}
		} catch (Exception e) {
			if(e instanceof HTTPException){
				exception = (HTTPException)e;
				ioexception = null;
				parseexception = null;
			} else if(e instanceof IOException){
				exception = null;
				ioexception = (IOException)e;
				parseexception = null;
			} else if(e instanceof ParseException){
				exception = null;
				ioexception = null;
				parseexception = (ParseException)e;
			}else {
			//	deberia caer aqui?
			}
		} finally {
			if (dataInput != null) {try {dataInput.close();} catch (IOException ex) {}}
			if (input != null) {try {input.close();} catch (IOException ex) {}}
			if (connection != null) {connection.disconnect();}
			if (exception != null) {throw exception;}
			if (ioexception != null) {throw ioexception;}
			if (parseexception != null) {throw parseexception;}
		}

    if((jsonObj != null) && (jsonObj instanceof Map)){
        return jsonObj;
    }
		//si buffer no es null entonces no era un json tampoco... este caso no deberia existir pero no se si sea necesario
		return buffer;
	}

	/**
	 * Funcion para obtener un contenido desde un web service validando que la respuesta traiga el campo HecticusSMSC
	 *
	 * <p>
	 *
	 * Levanta una conexion al WS y le pasa los parametros, luego revisa el estado de la conexion,
	 * si no es OK, lanza una excepcion. Si es OK procede a generar un string concatenando los campos
	 * de la respuesta. Luego convierte ese String a json y revisa si la respuesta trae el campo error, si error
	 * es diferente a 0 se manda una alarma. indiferentemente del error se retorna el json generado
	 *
	 *
	 * @param urlParameters		parametros a consultar al Web Service
	 * @return					Objeto que contiene un mapa con la respuesta del ws
	 * @throws javax.xml.ws.http.HTTPException
	 * @throws java.io.IOException
	 * @throws org.apache.http.ParseException
	 */
	@SuppressWarnings("rawtypes")
	public Object getSafeContentSMSC(String urlParameters) throws HTTPException,IOException, Exception {
		StringBuffer buffer = null;
		String line;
		int responseCode;
		HttpURLConnection connection = null;
		InputStream input = null;
		BufferedReader dataInput = null;

		HTTPException exception = null;
		IOException ioexception = null;

		//long startTime = System.currentTimeMillis();

		try {
			connection = (HttpURLConnection) url.openConnection();

			//set post data
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			if(urlParameters != null){
				connection.setRequestProperty("Content-Length", ""
					+ Integer.toString(urlParameters.getBytes().length));
			}

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			connection.setReadTimeout(60000);

			if (urlParameters != null) {
				//Send request
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();
			}

			responseCode = connection.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new HTTPException(responseCode);
			}

			buffer = new StringBuffer();
			input = connection.getInputStream();
			dataInput = new BufferedReader(new InputStreamReader(input));
			while ((line = dataInput.readLine()) != null) {
				buffer.append(line);
				buffer.append("\r\n");
			}
		} catch (HTTPException ex) {
			buffer = null;
			Utils.printToLog(this, null, "HTTP error " + ex.getStatusCode()+" URL:"+url+"?"+urlParameters, false, ex, "support-level-1", Config.LOGGER_ERROR);
			exception = ex;
		} catch (IOException ex) {
			buffer = null;
			Utils.printToLog(this, null, "HTTP error bad IO " + ex.getMessage()+" URL:"+url+"?"+urlParameters, false, ex, "support-level-1", Config.LOGGER_ERROR);
			ioexception = ex;
		} finally {
			if (dataInput != null) {try {dataInput.close();} catch (IOException ex) {}}
			if (input != null) {try {input.close();} catch (IOException ex) {}}
			if (connection != null) {connection.disconnect();}

			//devolver las exceptions
			if (exception != null) {throw exception;}
			if (ioexception != null) {throw ioexception;}

			//logger.info("WS_TIME: "+url+" -time:"+(System.currentTimeMillis()-startTime));
		}

		//revisamos si viene el tag error en el json
		if (buffer != null) {
			Object jsonObj = Utils.parseSafeJsonStringSMSC(buffer.toString());
			if (jsonObj instanceof Map) {
				Map map = (Map) jsonObj;
				if (map.containsKey("error") && map.containsKey("description")) {
					long errorCode = (Long)map.get("error");
					if(errorCode!=0){
						buffer = null;
						String emsg = "Error en el WS: " + url + " con parametros: " + urlParameters + ", Error: " + map.get("error") + " Description: " + map.get("description");
						Utils.printToLog(this,"Error en WS: " + url, emsg, true, null, "support-level-1", Config.LOGGER_ERROR);
					}
				}
			} else {
				if (jsonObj instanceof ArrayList) {
					// si es un array hacemos algo en especial? esto es lo mas comun
				}
			}

			return jsonObj;
		}

		//si buffer no es null entonces no era un json tampoco... este caso no deberia existir pero no se si sea necesario
		return buffer;
	}

	/**
	 * Funcion para obtener un contenido desde un web service
	 *
	 * <p>
	 *
	 * Levanta una conexion al WS y le pasa los parametros, luego revisa el estado de la conexion,
	 * si no es OK, lanza una excepcion. Si es OK procede a generar un string concatenando los campos
	 * de la respuesta. Se retorn lo que sea que traiga el WS ya que es externo, el handler se
	 * encargara de revisar si esta correcto o no
	 *
	 *
	 * @param urlParameters		parametros a consultar al Web Service
	 * @param post_params		flag para especificar que se usara POST
	 * @return					Objeto que contiene un string con la respuesta del ws
	 * @throws javax.xml.ws.http.HTTPException
	 * @throws java.io.IOException
	 * @throws org.apache.http.ParseException
	 */
	public Object getExternalContent(String urlParameters, boolean post_params) throws HTTPException,IOException {
		StringBuffer buffer = null;
		String line;
		int responseCode;
		HttpURLConnection connection = null;
		InputStream input = null;
		BufferedReader dataInput = null;

		HTTPException exception = null;
		IOException ioexception = null;

		//long startTime = System.currentTimeMillis();

		try {
			connection = (HttpURLConnection) url.openConnection();

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			connection.setReadTimeout(60000);

			//set post data
			if(post_params){
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");

				if(urlParameters != null){
					connection.setRequestProperty("Content-Length", ""
						+ Integer.toString(urlParameters.getBytes().length));
				}

				if (urlParameters != null) {
					//Send request
					DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
				}
			}

			responseCode = connection.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new HTTPException(responseCode);
			}

			buffer = new StringBuffer();
			input = connection.getInputStream();
			dataInput = new BufferedReader(new InputStreamReader(input));
			while ((line = dataInput.readLine()) != null) {
				buffer.append(line);
				buffer.append("\r\n");
			}
		} catch (HTTPException ex) {
			buffer = null;
			if(post_params){
				Utils.printToLog(this, null, "HTTP error " + ex.getStatusCode()+" URL:"+url+"?"+urlParameters, false, ex, "support-level-1", Config.LOGGER_ERROR);
			}else{
				Utils.printToLog(this, null, "HTTP error " + ex.getStatusCode()+" URL:"+url, false, ex, "support-level-1", Config.LOGGER_ERROR);
			}
			exception = ex;
		} catch (IOException ex) {
			buffer = null;
			if(post_params){
				Utils.printToLog(this, null, "HTTP error bad IO " + ex.getMessage()+" URL:"+url+"?"+urlParameters, false, ex, "support-level-1", Config.LOGGER_ERROR);
			}else{
				Utils.printToLog(this, null, "HTTP error bad IO " + ex.getMessage()+" URL:"+url, false, ex, "support-level-1", Config.LOGGER_ERROR);
			}
			ioexception = ex;
		} finally {
			if (dataInput != null) {try {dataInput.close();} catch (IOException ex) {}}
			if (input != null) {try {input.close();} catch (IOException ex) {}}
			if (connection != null) {connection.disconnect();}

			//devolver las exceptions
			if (exception != null) {throw exception;}
			if (ioexception != null) {throw ioexception;}

			//logger.info("WS_TIME: "+url+" -time:"+(System.currentTimeMillis()-startTime));
		}

		//retornamos lo que sea que traiga el WS ya que es externo, el handler se encargara de revisar si esta correcto o no
		return buffer;
	}

	/**
	 * Funcion para obtener un contenido desde un web service
	 *
	 * <p>
	 *
	 * Levanta una conexion al WS y le pasa los parametros, luego revisa el estado de la conexion,
	 * si no es OK, lanza una excepcion. Si es OK procede a generar un string concatenando los campos
	 * de la respuesta. Se retorn lo que sea que traiga el WS ya que es externo, el handler se
	 * encargara de revisar si esta correcto o no
	 *
	 *
	 * @param urlParameters		parametros a consultar al Web Service
	 * @param post_params		flag para especificar que se usara POST
	 * @return					Objeto que contiene un string con la respuesta del ws
	 * @throws javax.xml.ws.http.HTTPException
	 * @throws java.io.IOException
	 * @throws org.apache.http.ParseException
	 */
	public Object getMovileContent(String urlParameters, boolean post_params) throws HTTPException,IOException {
		StringBuffer buffer = null;
		String line;
		int responseCode;
		HttpURLConnection connection = null;
		InputStream input = null;
		BufferedReader dataInput = null;

		HTTPException exception = null;
		IOException ioexception = null;

		//long startTime = System.currentTimeMillis();

		try {
			connection = (HttpURLConnection) url.openConnection();

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			//connection.setReadTimeout(60000);
			connection.setReadTimeout(Config.getInt("movile-timeout-millis"));

			//set post data
			if(post_params){
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");

				if(urlParameters != null){
					connection.setRequestProperty("Content-Length", ""
						+ Integer.toString(urlParameters.getBytes().length));
				}

				if (urlParameters != null) {
					//Send request
					DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
				}
			}

			responseCode = connection.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new HTTPException(responseCode);
			}

			buffer = new StringBuffer();
			input = connection.getInputStream();
			dataInput = new BufferedReader(new InputStreamReader(input));
			while ((line = dataInput.readLine()) != null) {
				buffer.append(line);
				buffer.append("\r\n");
			}
		} catch (HTTPException ex) {
			buffer = null;
			if(post_params){
				Utils.printToLog(this, null, "HTTP error " + ex.getStatusCode()+" URL:"+url+"?"+urlParameters, false, ex, "support-level-1", Config.LOGGER_ERROR);
			}else{
				Utils.printToLog(this, null, "HTTP error " + ex.getStatusCode()+" URL:"+url, false, ex, "support-level-1", Config.LOGGER_ERROR);
			}
			exception = ex;
		} catch (IOException ex) {
			buffer = null;
			if(post_params){
				Utils.printToLog(this, null, "HTTP error bad IO " + ex.getMessage()+" URL:"+url+"?"+urlParameters, false, ex, "support-level-1", Config.LOGGER_ERROR);
			}else{
				Utils.printToLog(this, null, "HTTP error bad IO " + ex.getMessage()+" URL:"+url, false, ex, "support-level-1", Config.LOGGER_ERROR);
			}
			ioexception = ex;
		} finally {
			if (dataInput != null) {try {dataInput.close();} catch (IOException ex) {}}
			if (input != null) {try {input.close();} catch (IOException ex) {}}
			if (connection != null) {connection.disconnect();}

			//devolver las exceptions
			if (exception != null) {throw exception;}
			if (ioexception != null) {throw ioexception;}

			//logger.info("WS_TIME: "+url+" -time:"+(System.currentTimeMillis()-startTime));
		}

		//retornamos lo que sea que traiga el WS ya que es externo, el handler se encargara de revisar si esta correcto o no
		return buffer;
	}


	/**
	 * Funcion para obtener un contenido desde un web service
	 *
	 * <p>
	 *
	 * Levanta una conexion al WS y le pasa los parametros, luego revisa el estado de la conexion,
	 * si no es OK, lanza una excepcion. Si es OK procede a generar un string concatenando los campos
	 * de la respuesta. Luego convierte ese String a json y revisa si la respuesta trae el campo error, si error
	 * es diferente a 0 se manda una alarma. indiferentemente del error se retorna el json generado
	 *
	 *
	 * @param urlParameters		parametros a consultar al Web Service
	 * @return					Objeto que contiene un mapa con la respuesta del ws
	 * @throws javax.xml.ws.http.HTTPException
	 * @throws java.io.IOException
	 * @throws org.apache.http.ParseException
	 */
	public Object getContentExt(String urlParameters) throws HTTPException,IOException,ParseException {
		StringBuffer buffer = null;
		String line;
		int responseCode;
		HttpURLConnection connection = null;
		InputStream input = null;
		BufferedReader dataInput = null;
		int retryCounter = Config.getInt("ws-retry-count");
		int retryTime = Config.getInt("ws-retry-time");
		HTTPException exception = null;
		IOException ioexception = null;
		ParseException parseexception = null;
		try {
			while (retryCounter > 0) {
				try {
					connection = (HttpURLConnection) url.openConnection();
					//set post data
					connection.setRequestMethod("POST");
					connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					if (urlParameters != null) {
						connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
					}
					connection.setUseCaches(false);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					connection.setReadTimeout(60000);
					if (urlParameters != null) {
						//Send request
						DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
						wr.writeBytes(urlParameters);
						wr.flush();
						wr.close();
					}
					responseCode = connection.getResponseCode();
					if (responseCode != HttpURLConnection.HTTP_OK) {
						retryCounter--;
						Thread.sleep(retryTime);
						if(exception == null || exception.getStatusCode() != responseCode){
							exception = new HTTPException(responseCode);
						}
					} else {
						buffer = new StringBuffer();
						input = connection.getInputStream();
						dataInput = new BufferedReader(new InputStreamReader(input));
						while ((line = dataInput.readLine()) != null) {
							buffer.append(line);
							buffer.append("\r\n");
						}
						return buffer;
					}
				} catch (Exception ex) {
					retryCounter--;
					Thread.sleep(retryTime);
					if(retryCounter <= 0){
						throw ex;
					}
				}				
			}
		} catch (Exception e) {
			if(e instanceof HTTPException){
				exception = (HTTPException)e;
				ioexception = null;
				parseexception = null;
			} else if(e instanceof IOException){
				exception = null;
				ioexception = (IOException)e;
				parseexception = null;
			} else if(e instanceof ParseException){
				exception = null;
				ioexception = null;
				parseexception = (ParseException)e;
			}else {
			//	deberia caer aqui?
			}
		} finally {
			if (dataInput != null) {try {dataInput.close();} catch (IOException ex) {}}
			if (input != null) {try {input.close();} catch (IOException ex) {}}
			if (connection != null) {connection.disconnect();}
			if (exception != null) {throw exception;}
			if (ioexception != null) {throw ioexception;}
			if (parseexception != null) {throw parseexception;}
		}
                
        return buffer;
	}
	
}
