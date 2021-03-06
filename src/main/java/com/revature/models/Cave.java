package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/*
 * Caves and bears such that a relationship is defined when a bear
 * is in a specific cave
 *
 * One to many relationship such that 1 cave can have 0-n bears.
 * 
 */
@Entity
@Table(name="caves")
@Check(constraints = "char_length(name) > 1")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Cave {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, unique=true, length=40)
	private String name;
	private String location;

	/* cubic meter space */
	private double volume;
	
	@OneToMany
	@JoinColumn(name="cave_id")
	private List<Bear> bears;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public List<Bear> getBears() {
		return bears;
	}

	public void setBears(List<Bear> bears) {
		this.bears = bears;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bears == null) ? 0 : bears.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cave other = (Cave) obj;
		if (bears == null) {
			if (other.bears != null)
				return false;
		} else if (!bears.equals(other.bears))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(volume) != Double.doubleToLongBits(other.volume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cave [id=" + id + ", name=" + name + ", location=" + location + ", volume=" + volume + ", bears="
				+ bears + "]";
	}

	public Cave(int id, String name, String location, double volume, List<Bear> bears) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.volume = volume;
		this.bears = bears;
	}

	public Cave() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
