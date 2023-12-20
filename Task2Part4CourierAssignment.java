package Java_Control_Flow_Statements;

import java.util.ArrayList;
import java.util.List;

class DeliveryPerson {
    private String name;
    private double proximity; // Proximity to the shipment destination
    private double loadCapacity; // Load capacity of the delivery person

    public DeliveryPerson(String name, double proximity, double loadCapacity) {
        this.name = name;
        this.proximity = proximity;
        this.loadCapacity = loadCapacity;
    }

    // Getters for delivery person properties
    public String getName() {
        return name;
    }

    public double getProximity() {
        return proximity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }
}

class Parcel {
    private String destination;
    private double parcelLoad;

    public Parcel(String destination, double parcelLoad) {
        this.destination = destination;
        this.parcelLoad = parcelLoad;
    }

    // Getters for parcel properties
    public String getDestination() {
        return destination;
    }

    public double getParcelLoad() {
        return parcelLoad;
    }
}

public class Task2Part4CourierAssignment {
    public static void main(String[] args) {
        // Sample list of delivery persons and parcels
        List<DeliveryPerson> deliveryPersons = new ArrayList<>();
        deliveryPersons.add(new DeliveryPerson("Person A", 10.5, 100));
        deliveryPersons.add(new DeliveryPerson("Person B", 15.2, 80));
        deliveryPersons.add(new DeliveryPerson("Person C", 8.7, 120));

        List<Parcel> parcels = new ArrayList<>();
        parcels.add(new Parcel("Destination X", 70));
        parcels.add(new Parcel("Destination Y", 90));

        // Assign delivery persons to parcels based on criteria
        for (Parcel parcel : parcels) {
            DeliveryPerson selectedPerson = findBestDeliveryPerson(deliveryPersons, parcel);

            if (selectedPerson != null) {
                System.out.println("Assigned " + selectedPerson.getName() + " to parcel to " + parcel.getDestination());
                // Remove the assigned person to avoid assigning the same person to multiple parcels
                deliveryPersons.remove(selectedPerson);
            } else {
                System.out.println("No available person for parcel to " + parcel.getDestination());
            }
        }
    }

    // Find the best delivery person for a given parcel based on criteria
    private static DeliveryPerson findBestDeliveryPerson(List<DeliveryPerson> deliveryPersons, Parcel parcel) {
        DeliveryPerson selectedPerson = null;
        double minProximity = Double.MAX_VALUE;

        for (DeliveryPerson person : deliveryPersons) {
            // Criteria: Choose person with closest proximity and sufficient load capacity
            if (person.getProximity() < minProximity && person.getLoadCapacity() >= parcel.getParcelLoad()) {
                minProximity = person.getProximity();
                selectedPerson = person;
            }
        }

        return selectedPerson;
    }
}

