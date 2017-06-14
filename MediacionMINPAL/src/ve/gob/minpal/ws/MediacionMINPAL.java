package ve.gob.minpal.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.phdconsultores.ws.exception.ExcepcionServicio;



/**
 * Interfaz que expone el servicio de carga inicial que ejecuta un Kettle Job de Pentaho Data Integration
 * 
 * @author Dr. Pedro Nolasco Bonillo Ramos
 * @version Version 1
 * 
 */


@WebService(name = "MediacionMINPAL", targetNamespace = "http://ws.minpal.gob.ve/")
public interface MediacionMINPAL {
	
	/**
	 * Metodo que ejecuta un comando de mediacion de un ftp al sistema bi de minpal a traves de un Kettle Job de Pentaho Data Integration
	 * 
	 * @param	pentaho_directorio_ejecucion_data_integration	- Ruta del directorio del servidor donde esta instalado el Pentaho Data Integration (PDI)							-Directorio donde esta el pdi en el host
	 * @param	pentaho_repositorio_nombre						- Nombre del repositorio pentaho
	 * @param	pentaho_repositorio_usuario						- Usuario del repositorio pentaho
	 * @param	pentaho_repositorio_password					- Password del usuario del repositorio pentaho
	 * @param	pentaho_repositorio_ruta_job					- Directorio del repositorio donde se encuentra el job a ejecutar
	 * @param	pentaho_nombre_job								- Nombre del job a ejecutar
	 * @param	pentaho_directorio_logs							- Ruta del directorio para almacenar logs
	 * @param	pentaho_nivel_logs								- Nivel de los logs a ejecutar: Basic, Detailed, Debug, Rowlevel, Error, Nothing 

	 * @param	ftp_servidor_ip									- Direccion IP del servidor ftp remoto desde donde se tomara el archivo para importarlo en el BI de minpal
	 * @param	ftp_servidor_puerto								- Puerto del servidor ftp remoto desde donde se tomara el archivo para importarlo en el BI de minpal
     * @param	ftp_servidor_usuario							- Usuario para conectarse al servidor ftp remoto desde donde se tomara el archivo para importarlo en el BI de minpal
     * @param	ftp_servidor_password							- Password del usuario para conectarse al servidor ftp remoto desde donde se tomara el archivo para importarlo en el BI de minpal
     * @param	ftp_directorio_remoto							- Directorio que contiene los archivos dentro del servidor ftp remoto desde donde se tomara el archivo para importarlo en el BI de minpal
     * @param	ftp_directorio_remoto_procesados				- Directorio que contiene los archivos procesados dentro del servidor ftp remoto desde donde se tomara el archivo para importarlo en el BI de minpal
     * @param	ftp_expresion_regular_archivos					- Expresion regular para seleccionar los archivos que serán procesados, por ejemplo para todos los txt *.\*.txt
     * @param	ftp_directorio_local							- Directorio local que contiene los archivos procesados

     * @param	hadoop_servidor_ip								- Direccion ip del nodo de hadoop que recibira los datos
     * @param	hadoop_servidor_puerto							- Puerto del nodo de hadoop que recibira los datos
     * @param	hadoop_servidor_usuario							- Usuario del nodo de hadoop que recibira los datos
     * @param	hadoop_servidor_password						- Password del usuario del nodo de hadoop que recibira los datos
     * @param	hadoop_ruta_directorio							- Ruta del direcorio de hadoop donde se colocaran los datos
     * 
     * @param	solr_servidor_ip								- Direccion ip del nodo de solr que recibira los datos
     * @param	solr_servidor_puerto							- Puerto del nodo de solr que recibira los datos
     * @param	solr_core_destino								- Core de Solr que recibira los datos
	 * 
     * @return Un entero    									- Un valor entero (0 = exito y <> 0 error)
	 * @throws ExcepcionServicio								- Clase que levanta las excepciones de los servicios desarrollada por PhD 2014 Consultores C.A.
	 */
	
	@WebMethod(operationName = "mediacion", action = "urn:MediacionMINPAL")
	public int mediacion (			
			@WebParam(name = "pentaho_directorio_ejecucion_data_integration" ) @XmlElement(name = "pentaho_directorio_ejecucion_data_integration",required=true, nillable=false) String pentaho_directorio_ejecucion_data_integration,
			@WebParam(name = "pentaho_repositorio_nombre" ) @XmlElement(name = "pentaho_repositorio_nombre",required=true, nillable=false) String pentaho_repositorio_nombre,
			@WebParam(name = "pentaho_repositorio_usuario" ) @XmlElement(name = "pentaho_repositorio_usuario",required=true, nillable=false) String pentaho_repositorio_usuario,
			@WebParam(name = "pentaho_repositorio_password" ) @XmlElement(name = "pentaho_repositorio_password",required=true, nillable=false) String pentaho_repositorio_password,
			@WebParam(name = "pentaho_repositorio_ruta_job" ) @XmlElement(name = "pentaho_repositorio_ruta_job",required=true, nillable=false) String pentaho_repositorio_ruta_job,			
			@WebParam(name = "pentaho_nombre_job" ) @XmlElement(name = "pentaho_nombre_job",required=true, nillable=false) String pentaho_nombre_job,
			@WebParam(name = "pentaho_directorio_logs" ) @XmlElement(name = "pentaho_directorio_logs",required=true, nillable=false) String pentaho_directorio_logs,
			@WebParam(name = "pentaho_nivel_logs" ) @XmlElement(name = "pentaho_nivel_logs",required=true, nillable=false) String pentaho_nivel_logs,
			
			@WebParam(name = "ftp_servidor_ip" ) @XmlElement(name = "ftp_servidor_ip",required=true, nillable=false) String ftp_servidor_ip,
			@WebParam(name = "ftp_servidor_puerto" ) @XmlElement(name = "ftp_servidor_puerto",required=true, nillable=false) String ftp_servidor_puerto,
			@WebParam(name = "ftp_servidor_usuario" ) @XmlElement(name = "ftp_servidor_usuario",required=true, nillable=false) String ftp_servidor_usuario,
			@WebParam(name = "ftp_servidor_password" ) @XmlElement(name = "ftp_servidor_password",required=true, nillable=false) String ftp_servidor_password,
			@WebParam(name = "ftp_directorio_remoto" ) @XmlElement(name = "ftp_directorio_remoto",required=true, nillable=false) String ftp_directorio_remoto,
			@WebParam(name = "ftp_directorio_remoto_procesados" ) @XmlElement(name = "ftp_directorio_remoto_procesados",required=true, nillable=false) String ftp_directorio_remoto_procesados,
			@WebParam(name = "ftp_expresion_regular_archivos" ) @XmlElement(name = "ftp_expresion_regular_archivos",required=true, nillable=false) String ftp_expresion_regular_archivos,
			@WebParam(name = "ftp_directorio_local" ) @XmlElement(name = "ftp_directorio_local",required=true, nillable=false) String ftp_directorio_local,

			@WebParam(name = "hadoop_servidor_ip" ) @XmlElement(name = "hadoop_servidor_ip",required=true, nillable=false) String hadoop_servidor_ip,
			@WebParam(name = "hadoop_servidor_puerto" ) @XmlElement(name = "hadoop_servidor_puerto",required=true, nillable=false) String hadoop_servidor_puerto,
			@WebParam(name = "hadoop_servidor_usuario" ) @XmlElement(name = "hadoop_servidor_usuario",required=true, nillable=false) String hadoop_servidor_usuario,
			@WebParam(name = "hadoop_servidor_password" ) @XmlElement(name = "hadoop_servidor_password",required=true, nillable=false) String hadoop_servidor_password,
			@WebParam(name = "hadoop_ruta_directorio" ) @XmlElement(name = "hadoop_ruta_directorio",required=true, nillable=false) String hadoop_ruta_directorio,
			
			@WebParam(name = "solr_servidor_ip" ) @XmlElement(name = "solr_servidor_ip",required=true, nillable=false) String solr_servidor_ip,
			@WebParam(name = "solr_servidor_puerto" ) @XmlElement(name = "solr_servidor_puerto",required=true, nillable=false) String solr_servidor_puerto,
			@WebParam(name = "solr_core_destino" ) @XmlElement(name = "solr_core_destino",required=true, nillable=false) String solr_core_destino
		
						
			) throws ExcepcionServicio;
}

