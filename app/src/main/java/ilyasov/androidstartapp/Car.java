package ilyasov.androidstartapp;

import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Car {
    private String brand = "Audi";
    private String model = "Q8";
    private String color = "Red";
    private float consumption = 0.01f;
    private float petrolVolume = 10.0f;
    private float petrolTankCapacity = 55.0f;
    private List<String> passengers ;

    public Car(String brand, String model, String color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        passengers = Collections.emptyList();
    }

    @NonNull
    @Override
    public Car clone() throws CloneNotSupportedException {
        Car res = (Car) super.clone();
        res.passengers = new ArrayList<String>(passengers);
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final Car other = (Car) obj;
        float eps = 0.0001f;
        boolean passengersEq = false;
        Iterator<String> iterator = passengers.iterator();
        Iterator<String> iteratorOther = other.passengers.iterator();
        while(iterator.hasNext() && iteratorOther.hasNext()) {
            if (!iterator.next().equals(iteratorOther.next())) {
                return false;
            }
        }
        if (iterator.hasNext() || iteratorOther.hasNext()) {
            return false;
        }
        return (brand.equals(other.brand) &&
                model.equals(other.model) &&
                color.equals(other.color) &&
                Math.abs(consumption - other.consumption) < eps &&
                Math.abs(petrolVolume - other.petrolVolume) < eps &&
                Math.abs(petrolTankCapacity - other.petrolTankCapacity) < eps);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + color.hashCode();
        result += (int) (63 * consumption + 33 * petrolTankCapacity - 22 * petrolVolume);
        for (String passenger : passengers) {
            result = 31 * result + passenger.hashCode();
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {
        return String.join("\n",
                "Brand: " + brand,
                "Model: " + model + color,
                "Petrol tank capacity: " + petrolTankCapacity
                );
    }

    public void fillTank(float liters) {
        petrolVolume = Math.min(petrolTankCapacity, petrolVolume + liters);
        if (petrolVolume == petrolTankCapacity) {
            Log.d("CarLog", "Your tank is full of petrol");
        }
    }

    public void addPassenger(String name) {
        passengers.add(name);
        Log.d("CarLog", "The new passenger - " + name + "sitting into the car!");
    }

    public void drive(int kms) {
        Log.d("CarLog", "You have reached " + kms + " kilometers!");
        float d_petrol = consumption * kms;
        petrolVolume -= d_petrol;
        Log.d("CarLog", "You spent " + d_petrol + " liters of petrol!");
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String newBrand) {
        brand = newBrand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String newModel) {
       model = newModel;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String newColor) {
        color = newColor;
    }
    public float getPetrolTankCapacity() {
        return petrolTankCapacity;
    }
    public void setPetrolTankCapacity(float newCapacity) {
        if (newCapacity >= 0) {
            petrolTankCapacity = newCapacity;
        }
    }
    public float getConsumption() {
        return consumption;
    }
    public void setConsumption(float newConsumption) {
        if (newConsumption >= 0) {
            consumption = newConsumption;
        }
    }
}
