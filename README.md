Covid 19 -entrega a domicilio

Fase cero

En epoca de Covid se requiere crear un sistema de entregas a domicilio con las siguientes condiciones:
 
Entrega en un area de 10 cuadras a la redonda del punto de partida simulado por un plano cartesiano con coordenadas X,Y donde cada cuadra es una unidad
El dron siempre parte de la posicion (0,0,N) equivalente a (X,Y,P), donde X  es el eje horizontal, la Y el vertical y la P el punto cardinal (N norte, S sur, E oriente, W occidente) 

El dron entrega un maximo de 4 pedidos por despacho, partiendo desde (0,0,N)
El dron lee el pedido desde un archivo de texto llamado pedidos.txt donde estaran como maximo las 4 coordenadas que corresponden a los 4 pedidos
Cada coordenada contiene los movimientos que debe hacer el dron para llegar al punto de entrega donde la letra F indica avance al frente, la letra L indica giro a la izquierda, la letra R indica giro a la derecha.
El sistema debe entregar un reporte con las coordenadas de entrega por cada pedido en el formato "(X,Y) orientacion": ejemplo (1,2) orientacion Sur
El archivo de salida debe llamarse reporteEntregas.txt
El dron realiza todas las entregas en un solo despacho y al final regresa al punto de partida (0,0,N)

Ejemplo de un pedido.txt

FFFFFFFFFF - Partiendo de 0,0,N significa que avanzo 10 cuadras sin hacer giros, el punto de entrega seria 0,10,N
LFFRFFF - Partiendo desde 0,0,N significa, giro izquierda, avanza dos cuadras, giro derecha, avanza tres cuadras, punto final (-2,3,N)

Ejemplo de reporteEntregas.txt
(0,10) orientacion Norte
(-2,3) orientacion Norte

Si logras hacer ese, le agregamos algunas funcionalidades

Cargar Direcciones
El programa recibe un archivo con máximo 4 coordenadas o filas.
Decisión: si recibe más coordenadas o sea más de 4 filas, el sistema sólo tomará las 4 primeras y descartará las siguientes pero realizará una notificación de que recibió más por si sirve de algo indicarlo.
El programa deberá ingresar cada coordenada en un arreglo que contendrá tantas casillas como filas y estará dimensionado por el tamaño de la cantidad de letras del string. 
El programa deberá validar que las letras que contenga cada línea que se llevará al arreglo sean: “F”, “R”, “L”. no podrá tener otro tipo de caracteres y si los trae el programa notificará que esa dirección está corrupta y la descartará.
El programa cuando haya terminado de cargar el archivo y sus 4 líneas máximo, indicará que han sido cargadas las direcciones, cuantas están ok, cuántas tienen problemas y de qué tipo y cuáles han sido las buenas.
Debe validar que las direcciones no se salgan del rango especificado de 10 cuadras a la redonda o del parámetro que se indique.

Transformar direcciones en coordenadas
El sistema deberá transformar las direcciones enviadas en coordenadas de un plano cartesiano, calculándolas siempre desde el punto cero y dejándolas en el formato (x, y, Orientación). Ej: ejemplo (1,2) orientacion Sur.
Las direcciones le quedarán en ambos formatos.
Retorna la coordenada cartesiana.

Calcular ruta
El sistema debe estar en capacidad de calcular una ruta de 2 rectas cuando le envían dos coordenadas de plano cartesiano, una de origen y otra de fin. Este cálculo lo realizará con dos líneas rectas y máximo un giro. Cuando tenga al menos un giro, el sistema decidirá aleatoriamente la dirección de la primera recta para que no sea predecible siempre en las mismas direcciones.
El sistema siempre iniciará a hacer la primera entrega desde el punto (0,0). Ese será el origen de la primera entrega.
El sistema cuando haya entregado un pedido, la dirección de entrega del pedido inmediatamente anterior, será su nueva dirección de origen. El sistema preguntará si existe otro pedido y en caso afirmativo, Si no existe otro pedido el 

Entregar pedido
Recibe dirección de origen y dirección destino y la entrega.
Traza la ruta que le entrega otra función y hace la entrega.

Regresar al origen
Recibe dirección de origen y la dirección destino siempre será 0,0,N y hace la entrega.
Traza la ruta que le entrega otra función y regresa al origen.

Validar ruta
Recibe una ruta en cualquiera de los dos formatos e indica si la dirección es válida dentro de los parámetros y condiciones establecidas y retorna un verdadero o falso y una descripción en caso de ser falso.

Main
{
	//Declaro la variable pública con las direcciones
	Public pvarDirections as Array;
	//Declaro la variable pública del número de direcciones válidas
	Public pvarDirectionsAmount as Num
fileArchivo = LoadFile(rutaArchivo)



Obtengo Archivo
Convierto archivo en arreglo con las direcciones
Valido que las direcciones estén ok de acuerdo a políticas
Convierto las direcciones en coordenadas.
Inició las entregas
Cada que una entrega se hace, se inicia una nueva entrega
Cuando termina las entregas regresa a casa.

funGetParameter() //Obtengo valores de parámetros generales del sistema
funGetFile() //Obtengo Archivo
funConvertFileInAddress(varFile) //Convierto las filas de archivo en dirección en Arreglo y digo si estan ok
funValidAddressSintax() //Valido que cada dirección sea ok
funConvertAddressToCordinates() //Convierte las direcciones enviadas en coordenadas
funGenerateRoute(prmArrayOrigin, prmArrayDestiny) //Crea ruta desde un punto a otro para que sea seguida por el Drone
funGenerateDeliveries() //Empieza a hacer las entregas
funDeliveryOrder() //Entrega un pedido
funGoHome() //Envía el Drone a casa
funArrayPisitionsD1() //Indica cuántas filas existen en la primera dimensión del arreglo
funDroneForward() //Mueve el drone hacia adelante el número de bloques indicado
funDronePosition() //Indica al Drone si se ubica hacia el norte, sur, este, oeste.
funConfig() //Configurar variables de inicio y parámetros generales
funReporte1() //Entrega un reporte de las entregas del día


varFile = funGetFile() //Obtengo Archivo
varArrayAddress = funConvertFileInAddress(varFile);
varArrayPositions = funArrayPisitionsD1(varArrayAddress);
varCounter1 = 1
// Crear las coordenadas cartesianas para cada dirección en letras
For 1 to varArrayPositions
{
	varArrayAddress(varCounter1,3) = funConvertAddressToCordinates(varArrayAddress(varCounter1,1));
	varCounter1 = varCounter + 1;
}

varCounter1 = 1;
varAmountOrders = funGetParameter(“AmountOrders”);
	varOrigin = “0,0,N”;
For 1 to varAmountOrders
	{
		varOrderOk = funDeliveryOrder(varOrigin,varArrayAddress(varCounter1,3));
		varArrayAddress(varCounter1,4) = varOrderOk;
		varOrigin = varArrayAddress(varCounter1,3);
		varCounter = varCounter + 1;
	}

funGoHome();


End;
}

// Recibe dos coordenadas cartesianas, genera una ruta y mueve el drone desde el origen al destino

Function funDeliveryOrder(prmOrigin, prmDestiny)
{
	If prmOrigin = prmDestiny Then Exit;
	
	varRuta = funCalculateRoute(prmOrigin, prmDestiny)
	funDronePosition(varRuta(1)); //Coloque el Drone en posición hacia dónde avanzará “N”, “S”, “E”, “W”.
	If varRuta(2) > “0” Then //pregunta si debe avanzar o quedarse quieto (cuando entregue en la misma parte)
	{
		funDroneForward(varRuta(2)); //avanza el número de bloques
	}
	
	If varRuta(3) <> “” Then 
	{
		funDronePosition(varRuta(3)); //Coloque el Drone en posición para avanzar “N”, “S”, “E”, “W”.
		If varRuta(4) > 0 Then
		{
			funDroneForward(varRuta(4)); //avanza el número de bloques
		}
}
}


//Calcula una ruta a partir de un origen y destino cartesianos
Function funCalculateRoute(prmOrigin, prmDestiny)
{
	varXOrigin = Extract(prmOrigin, “,”, 1);
	varYOrigin = Extract(prmOrigin, “,”, 2);
	varXDestiny = Extract(prmOrigin, “,”, 1);
	varYDestiny = Extract(prmOrigin, “,”, 2);
	varTurnStartX = “”;
varTurnStartY = “”;

	If varXOrigin > varXDestiny Then
{
	varTurnStartX = “E”;
	varBlocksX = varXOrigin - varXDestiny;
} 
Else
{
	varTurnStartX = “W”;
}
varBlocksX = varXOrigin - varXDestiny;

If varYOrigin > varYDestiny Then
{
	varTurnStartY = “S”;
} 
Else
{
	varTurnStartY = “N”;
}
varBlocksY = varYOrigin - varYDestiny;

varRoute = “”;
Return varRoute;
}



//Convierto las filas de archivo en dirección en Arreglo
Function funConvertFileInAddress (File prmDirectons) as Array
{
	//Extraigo el número de filas del archivo
	varLines = LinesAmount(prmDirectons); 
	//Dimension for the array
	varArrayDirections = (varLines,2)
	// Create a counter
	varCounter1 = 1
//Ask if the amount on lines are superior to zero (0) go ahead else return array empty and message
If varLines = 0 then
{
	varArrayDirections(1,1) = Null
	varArrayDirections(1,2) = “There is not directions in file”
	Return varArrayDirections
}

//Valid directions its ok
	For 0 until varLines 
	{
		varArrayDirections(varCounter1,1) = prmDirectons.Line(varCounter1);
		varArrayDirections(varCounter1,2)=funValidAddressSintax(varArrayDirections(varCounter1,1));	
		varCounter1 = varCounter1 + 1
	}
	Return ArrayDirections
}
Function funValidDirectionsSintax(String prmDirection)
{
	varLength = Lengt(prmDirection);
	varCounter1 = 1;
	If varLength = 0 Then Return False

	For 0 to Length
	{
		varCounter1 = varCounter1 +1;
		If prmDirection(varCounter1)<> “F” or “R” or “L” Then
			Return False;
	}
	Return True;
}


