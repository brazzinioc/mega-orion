import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // opciones/funcionalidades del sistema
        String[] options = {
                "Ingresar nuevo equipo al inventario",
                "Actualizar marca de un equipo",
                "Actualizar modelo de un equipo",
                "Actualizar el tipo de un equipo",
                "Actualizar el estado de un equipo",
                "Sacar reporte de inventario"
        };

        // variables generales
        int option = 0;
        boolean quit = false;
        int quantity = 0;
        Scanner keyboard = new Scanner(System.in);

        // variables para el manejo de inventario
        final String[] DEVICE_TYPES = { "SD", "Monitor", "Laptop", "Impresora" };
        final String[] DEVICE_STATUSES = { "SD", "Malogrado", "Nuevo", "En mantenimiento", "Reparado"};

        // muestra mensaje de bienvenida
        showWelcome();

        try {
            System.out.print("* Ingrese cantidad de equipos a inventariar: ");
            quantity = keyboard.nextInt();
        }
        catch(Exception e) {
            // System.out.println(e);
            quantity = 0;
        }

        if (quantity == 0 || quantity < 0) {
            System.out.println("\nOops, por favor ingrese un valor mayor a cero.");
            System.exit(1);
        }

        // variables para el manejo de inventario
        String[] serialNumbers = new String[quantity];
        String[] models = new String[quantity];
        String[] brands = new String[quantity];
        int[] types = new int[quantity];
        int[] statuses = new int[quantity];

        while(!quit) {

            // muestra las opciones del sistema
            showOptions(options);

            // Solicita opcion al usuario
            try {
                System.out.print("* Ingrese una opcion: ");
                option = Integer.parseInt(keyboard.next());
            }
            catch(Exception e) {
                // System.out.println(e);
                option = 0;
            }

            // Evalua cada opcion para ejecutar el metodo que corresponde
            switch (option) {
                case 1:
                    createDevice(serialNumbers, models, brands, types, statuses, DEVICE_TYPES, DEVICE_STATUSES, keyboard);
                    break;

                case 2:
                    updateModel(serialNumbers, models, brands, types, statuses, DEVICE_TYPES, DEVICE_STATUSES, keyboard);
                    break;

                case 3:
                    System.out.println("option 3");
                    break;

                case 4:
                    System.out.println("option 4");
                    break;

                case 5:
                    System.out.println("option 5");
                    break;

                case 6:
                    System.out.println("option 6");
                    break;

                default:
                    System.out.println("Oops, parece que has ingresado una opción incorrecta.");
                    break;
            }

            // salida del sistema
            System.out.print("\n\n*Desea salir del sistema? (S)(N): ");
            quit = keyboard.next().equals("S");
        }

        System.out.println("\n¡Hasta la próxima y que tengas un maravilloso día!");
    }

    public static void showWelcome() {
        System.out.println("---------------------------------------------");
        System.out.println("    Bienvenid@ al sistema de Inventario      ");
        System.out.println("---------------------------------------------");
    }

    public static void showOptions(String[] options) {
        if (options.length > 0) {

            System.out.println("* Opciones del sistema: ");

            for (int i = 0; i < options.length; i++) {
                System.out.printf("\t%d) %s\n", (i + 1), options[i]);
            }

        } else {
            System.out.println("Estamos trabajando en las opciones del sistema :)");
        }
    }

    public static void createDevice(
            String[] serialNumbers,
            String[] models,
            String[] brands,
            int[] types,
            int[] statuses,
            final String[] DEVICE_TYPES,
            final String[] DEVICE_STATUSES,
            Scanner keyboard
    ) {
        int arrayIndex = -1;

        // busca espacio para inventariar equipo
        for (int i = 0; i < serialNumbers.length; i++) {
            if (serialNumbers[i] == null) {
                arrayIndex = i;
                break;
            }
        }

        // valida si hay espacio para inventariar equipo
        if (arrayIndex >= 0) {

            String serialNumber, model, brand;
            int type, status;
            boolean alreadyExist = false;

            System.out.println("\n- Inventario de un nuevo equipo");
            System.out.print("\t* Ingrese serie: ");
            serialNumber = keyboard.next().toUpperCase();

            for (int i = 0; i < serialNumbers.length; i++) {
                if (serialNumbers[i] != null && serialNumbers[i].equals(serialNumber)) {
                    alreadyExist = true;
                    break;
                }
            }

            if (!alreadyExist) {
                serialNumbers[arrayIndex] = serialNumber;

                System.out.print("\t* Ingrese modelo: ");
                model = keyboard.next();
                models[arrayIndex] = model;

                System.out.print("\t* Ingrese marca: ");
                brand = keyboard.next();
                brands[arrayIndex] = brand;

                String typeOptions = "";
                for (int i = 0; i < DEVICE_TYPES.length; i++) {
                    typeOptions += (i + 1) + ") " + DEVICE_TYPES[i] + " ";
                }
                System.out.printf("\t* Seleccione e ingrese un tipo.\n\t\t%s: ", typeOptions);
                type = (keyboard.nextInt() - 1);
                types[arrayIndex] = type;

                String statusOptions = "";
                for (int i = 0; i < DEVICE_STATUSES.length; i++) {
                    statusOptions += (i + 1) + ") " + DEVICE_STATUSES[i] + " ";
                }
                System.out.printf("\t* Seleccione e ingrese un estado.\n\t\t%s: ", statusOptions);
                status = (keyboard.nextInt() - 1);
                statuses[arrayIndex] = status;

                System.out.printf("\tEquipo registrado con exito!.\n\t\tSerie: %s\n\t\tModelo: %s\n\t\tMarca: %s\n\t\tTipo: %s\n\t\tEstado: %s", serialNumbers[arrayIndex], models[arrayIndex], brands[arrayIndex], DEVICE_TYPES[type], DEVICE_STATUSES[status]);
            } else {
                System.out.printf("El equipo con serie %s ya se encuentra registrado.", serialNumber);
            }

        } else {
            System.out.println("El inventario ya se encuentra lleno.");
        }
    }

    public static void updateModel(
            String[] serialNumbers,
            String[] models,
            String[] brands,
            int[] types,
            int[] statuses,
            final String[] DEVICE_TYPES,
            final String[] DEVICE_STATUSES,
            Scanner keyboard
    ) {
        System.out.println("\n- Actualizacion de marca de un equipo");
        int arrayIndex = -1;

        System.out.print("\t* Ingrese serie: ");
        String serialNumber = keyboard.next();

        // busca equipo en el inventariar
        for (int i = 0; i < serialNumbers.length; i++) {
            if (serialNumbers[i] != null && serialNumbers[i].equals(serialNumber)) {
                arrayIndex = i;
                break;
            }
        }

        // actualiza el modelo del equipo
        if (arrayIndex >= 0) {
            String currentModel = models[arrayIndex];
            System.out.printf("\t- Modelo actual: %s", currentModel);

            System.out.print("\t* Ingrese nuevo modelo: ");
            String newModel = keyboard.next();

            models[arrayIndex] = newModel;
            System.out.printf("\tEquipo registrado con exito!.\n\t\tSerie: %s\n\t\tModelo: %s\n\t\tMarca: %s\n\t\tTipo: %s\n\t\tEstado: %s", serialNumbers[arrayIndex], models[arrayIndex], brands[arrayIndex], DEVICE_TYPES[types[arrayIndex]], DEVICE_STATUSES[statuses[arrayIndex]]);

        } else {
            System.out.printf("El equipo con serie %s no se encuentra en el inventario.", serialNumber);
        }
    }
}
