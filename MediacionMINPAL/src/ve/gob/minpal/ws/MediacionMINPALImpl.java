package ve.gob.minpal.ws;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import com.phdconsultores.ws.exception.ExcepcionServicio;



/**
 * Clase que implementa el servicio de mediacion que ejecuta un Kettle Job de Pentaho Data Integration para la carga de datos en el servidor bi de minpal 
 * 
 * @author Dr. Pedro Nolasco Bonillo Ramos
 * @version 1.0
 *
 */

@WebService(targetNamespace = "http://ws.minpal.gob.ve/", endpointInterface = "ve.gob.minpal.ws.MediacionMINPAL",
portName = "MediacionMINPALPort", serviceName = "MediacionMINPAL")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class MediacionMINPALImpl implements MediacionMINPAL {
	
	public MediacionMINPALImpl(){}
	
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
     * @param	ftp_expresion_regular_archivos					- Expresion regular para seleccionar los archivos que ser�n procesados, por ejemplo para todos los txt *.\*.txt
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
	
	public int mediacion(			
			String pentaho_directorio_ejecucion_data_integration,
			String pentaho_repositorio_nombre,
			String pentaho_repositorio_usuario,
			String pentaho_repositorio_password,
			String pentaho_repositorio_ruta_job,					
			String pentaho_nombre_job,
			String pentaho_directorio_logs,
			String pentaho_nivel_logs,
			String ftp_servidor_ip,
			String ftp_servidor_puerto,
			String ftp_servidor_usuario,
			String ftp_servidor_password,
			String ftp_directorio_remoto,			
			String ftp_directorio_remoto_procesados,
			String ftp_expresion_regular_archivos,
			String ftp_directorio_local,
			String hadoop_servidor_ip,
			String hadoop_servidor_puerto,
			String hadoop_servidor_usuario,			
			String hadoop_servidor_password,
			String hadoop_ruta_directorio,
			String solr_servidor_ip,			
			String solr_servidor_puerto,
			String solr_core_destino
			) throws ExcepcionServicio {
		
		int iExitValue = 99;
		String commandScript = null;				
		FileWriter scriptfile = null;
        PrintWriter pwriter = null;
        Date fechaHoy = new Date();
        pentaho_directorio_logs = pentaho_directorio_logs + "/" + ftp_servidor_usuario + "_MINPAL_" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHoy) + "_" + new SimpleDateFormat("HH:mm:ss.SSS").format(fechaHoy);
        String filename =  ftp_servidor_usuario + "_MINPAL_" + new SimpleDateFormat("yyyy-MM-dd").format(fechaHoy) + "_" + new SimpleDateFormat("HH:mm:ss").format(fechaHoy);
		
        commandScript = pentaho_directorio_ejecucion_data_integration + "/./kitchen.sh" + " -rep:" + pentaho_repositorio_nombre + " -job:\"" + pentaho_nombre_job +
        		"\" -param:FTP_SERVIDOR_IP=\"" + ftp_servidor_ip +
        		"\" -param:FTP_SERVIDOR_PUERTO=\"" + ftp_servidor_puerto +
        		"\" -param:FTP_SERVIDOR_USUARIO=\"" + ftp_servidor_usuario +
        		"\" -param:FTP_SERVIDOR_PASSWORD=\"" + ftp_servidor_password +
        		"\" -param:FTP_DIRECTORIO_REMOTO=\"" + ftp_directorio_remoto +
        		"\" -param:FTP_SERVIDOR_REMOTO_PROCESADOS=\"" + ftp_directorio_remoto_procesados +
        		"\" -param:FTP_EXPRESION_REGULAR_ARCHIVOS=\"" + ftp_expresion_regular_archivos +
        		"\" -param:FTP_DIRECTORIO_LOCAL=\"" + ftp_directorio_local +
        		"\" -param:HADOOP_SERVIDOR_IP=\"" + hadoop_servidor_ip +
        		"\" -param:HADOOP_SERVIDOR_PUERTO=\"" + hadoop_servidor_puerto	 +
        		"\" -param:HADOOP_SERVIDOR_USUARIO=\"" + hadoop_servidor_usuario +
        		"\" -param:HADOOP_SERVIDOR_PASSWORD=\"" + hadoop_servidor_password +
        		"\" -param:HADOOP_RUTA_DIRECTORIO=\"" + hadoop_ruta_directorio +
        		"\" -param:SOLR_SERVIDOR_IP=\"" + solr_servidor_ip +
        		"\" -param:SOLR_SERVIDOR_PUERTO=\"" + solr_servidor_puerto	 +
        		"\" -param:SOLR_CORE_DESTINO=\"" + solr_core_destino +	
        		"\" -param:PENTAHO_DIRECTORIO_LOGS=\"" + pentaho_directorio_logs +       		        		
        		"\" -dir:" + pentaho_repositorio_ruta_job +
				" -user:" + pentaho_repositorio_usuario + " -pass:" + pentaho_repositorio_password + " -level:" + pentaho_nivel_logs + " -log:" + pentaho_directorio_logs + "/" + filename + ".log";
				
		//SE CREA EL NUEVO DIRECTORIO DENTRO DE DIRLOGS
		CommandLine oCmdLine0 = CommandLine.parse("mkdir -p " + pentaho_directorio_logs);
		//SE AGREGA EL COMANDO QUE LE OTORGA PERMISO DE EJECUCION AL ARCHIVO
		CommandLine oCmdLine1 = CommandLine.parse("chmod +x " + pentaho_directorio_logs + "/" + filename + "_command.sh" );
		//SE AGREGA EL COMANDO QUE EJECUTA EL ARCHIVO
		CommandLine oCmdLine2 = CommandLine.parse(pentaho_directorio_logs + "/./" + filename + "_command.sh");
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        oDefaultExecutor.setExitValue(0);
        
        try {
            //SE EJECUTA EL COMANDO PARA CREAR LA CARPETA
        	iExitValue = oDefaultExecutor.execute(oCmdLine0);
        	//UNA VEZ CREADA LALA RUTA dirLogs 
    		//SE CREA EL ARCHIVO .sh CON EL COMANDO COMPLETO
    		try{    			
    			scriptfile = new FileWriter(pentaho_directorio_logs + "/" + filename + "_command.sh");
    			pwriter = new PrintWriter(scriptfile);
    			pwriter.println(commandScript);
    		} catch (Exception io) {
    			throw new ExcepcionServicio(io.getMessage());
    		} finally {
    			try {
    				if (null != scriptfile) scriptfile.close();
    			} catch (Exception io2){
    				throw new ExcepcionServicio(io2.getMessage());
    			}			
    		}
    		
        	//SE EJECUTA EL COMANDO PARA DERLE PERMISO
        	iExitValue = oDefaultExecutor.execute(oCmdLine1);
        	//SE EJECUTA EL COMANDO QUE INICIA LA EJECUCION DEL ARCHIVO 
        	iExitValue = oDefaultExecutor.execute(oCmdLine2);        	
        
        } catch (ExecuteException ex) {
            
        	throw new ExcepcionServicio(ex.getMessage());
        	
        } catch (IOException ie) {
            
        	throw new ExcepcionServicio(ie.getMessage());
        	
        } catch(Exception e) {
        	
        	throw new ExcepcionServicio(e.getMessage());
        }
        
		return iExitValue;
	}

}

