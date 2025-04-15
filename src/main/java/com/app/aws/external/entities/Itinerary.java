package com.app.aws.external.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Itinerary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itinerary_id;
	private String source;
	private String destination;
	private int duration;
	private boolean isactive;
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public long getItinerary_id() {
		return itinerary_id;
	}

	public void setItinerary_id(long itinerary_id) {
		this.itinerary_id = itinerary_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Itinerary() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(destination, duration, isactive, itinerary_id, price, source);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerary other = (Itinerary) obj;
		return Objects.equals(destination, other.destination) && duration == other.duration
				&& isactive == other.isactive && itinerary_id == other.itinerary_id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(source, other.source);
	}

	@Override
	public String toString() {
		return "Itinerary [itinerary_id=" + itinerary_id + ", source=" + source + ", destination=" + destination
				+ ", duration=" + duration + ", isactive=" + isactive + ", price=" + price + "]";
	}
}
