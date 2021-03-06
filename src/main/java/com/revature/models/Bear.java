package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*
 * We will use JPA annotations to define this class as an entity
 */

@Entity // Must be used if we want Hibernate to manage it
// @Table - Optional annotation that provides additional table configuration
@Table(name="bears")
@Check(constraints = "id > 0")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonIgnoreProperties("@id")
@NamedQuery(name = "getBearByBreed", query = "FROM Bear WHERE breed = :breed")
public class Bear {
	// BY DEFAULT all properties will be treated as columns
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String breed;
	private String color;
	
	// @Column - Optional column configuration
	@Column(name="people_eaten")
	private int peopleEaten;

	/** Height in meters when on two legs **/
	@Column(columnDefinition = "float")
	private double height;
	
//	@Transient -- @Transient properties are not stored in the database
	private double weight;
	
	@Column(unique=true)
	private String name;

	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="honey_jar_id")
	private HoneyJar honeyJar;
	
	@ManyToOne
	@JoinColumn(name="cave_id")
	private Cave cave;
	
	@ManyToMany
	@JoinTable(name="bear_cubs", joinColumns = { @JoinColumn(name="parent_id")},
					inverseJoinColumns = { @JoinColumn(name="cub_id") })			
	private List<Bear> cubs;
	
	@ManyToMany
	@JoinTable(name="bear_cubs", joinColumns = { @JoinColumn(name="cub_id")},
			inverseJoinColumns = { @JoinColumn(name="parent_id") })	
	private List<Bear> parents;
	
	public List<Bear> getCubs() {
		return cubs;
	}

	public void setCubs(List<Bear> cubs) {
		this.cubs = cubs;
	}

	public List<Bear> getParents() {
		return parents;
	}

	public void setParents(List<Bear> parents) {
		this.parents = parents;
	}

	public Cave getCave() {
		return cave;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPeopleEaten() {
		return peopleEaten;
	}

	public void setPeopleEaten(int peopleEaten) {
		this.peopleEaten = peopleEaten;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public HoneyJar getHoneyJar() {
		return honeyJar;
	}

	public void setHoneyJar(HoneyJar honeyJar) {
		this.honeyJar = honeyJar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + peopleEaten;
		temp = Double.doubleToLongBits(weight);
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
		Bear other = (Bear) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (peopleEaten != other.peopleEaten)
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	public Bear(int id, String breed, String color, int peopleEaten, double height, double weight, String name) {
		super();
		this.id = id;
		this.breed = breed;
		this.color = color;
		this.peopleEaten = peopleEaten;
		this.height = height;
		this.weight = weight;
		this.name = name;
	}

	public Bear() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Bear [id=" + id + ", breed=" + breed + ", color=" + color + ", peopleEaten=" + peopleEaten + ", height="
				+ height + ", weight=" + weight + ", name=" + name + "]";
	}

}
