package ar.edu.unlp.info.lifia.grupo1.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Design patterns: Singleton + Factory
 */
public class ProjectPrototypeFactory {

	private static ProjectPrototypeFactory instance;
	
	private MovieProject prototypeMovieProject;
	private GameProject prototypeGameProject;
	Set<Section> defaultSections = new HashSet<Section>();
	Set<Section> movieSections = new HashSet<Section>();
	Set<Section> gameSections = new HashSet<Section>();
	
	public static ProjectPrototypeFactory getInstance() {
		if(instance == null) {
			instance = new ProjectPrototypeFactory();
		}
		
		return instance;
	}
	
	private ProjectPrototypeFactory() {
		
		/**
		 * Default sections
		 */
		defaultSections.add(new Section("Nombre"));
		defaultSections.add(new Section("Descripcion"));
		defaultSections.add(new Section("Objetivo"));
		defaultSections.add(new Section("Dinero requerido"));	
		
		/**
		 * Game sections
		 */
		
		this.gameSections.add(new Section("Nombre en codigo"));
		
		
		/**
		 * Movie Sections
		 */
		
		this.movieSections.add(new Section("Genero"));
		this.movieSections.add(new Section("Guion"));
		
		/**
		 * Game project prototype definition
		 */
		
		this.prototypeGameProject = new GameProject(new Active(),new HashSet<Section>());
		
		for (Section currentGameSection : this.gameSections) {
			this.prototypeGameProject.addSection(currentGameSection);
		}			
				
		/**
		 * Movie project prototype definition
		 */
		
		this.prototypeMovieProject = new MovieProject(new Active(),new HashSet<Section>());
		
		for (Section currentMovieSection : this.movieSections) {
			this.prototypeMovieProject.addSection(currentMovieSection);
		}
		
		/**
		 * Default sections adding
		 */
		
		for (Section currentDefaultSection : this.defaultSections) {
			prototypeGameProject.addSection(currentDefaultSection);
			prototypeMovieProject.addSection(currentDefaultSection);
		}
		
	}
	
	public MovieProject createMovieProjectFromPrototype() {
		return (MovieProject) this.getPrototypeMovieProject().clone();
	}
	
	public GameProject createGameProjectFromPrototype() {
		return (GameProject) this.getPrototypeGameProject().clone();
	}

	public MovieProject getPrototypeMovieProject() {
		return prototypeMovieProject;
	}

	public void setPrototypeMovieProject(MovieProject prototypeMovieProject) {
		this.prototypeMovieProject = prototypeMovieProject;
	}

	public GameProject getPrototypeGameProject() {
		return prototypeGameProject;
	}

	public void setPrototypeGameProject(GameProject prototypeGameProject) {
		this.prototypeGameProject = prototypeGameProject;
	}

	public Set<Section> getDefaultSections() {
		return defaultSections;
	}

	public void setDefaultSections(Set<Section> defaultSections) {
		this.defaultSections = defaultSections;
	}

	public Set<Section> getMovieSections() {
		return movieSections;
	}

	public void setMovieSections(Set<Section> movieSections) {
		this.movieSections = movieSections;
	}

	public Set<Section> getGameSections() {
		return gameSections;
	}

	public void setGameSections(Set<Section> gameSections) {
		this.gameSections = gameSections;
	}
	
	public Set<Section> getAllSections() {
		HashSet<Section> allSections = new HashSet<Section>();
		
		for (Section currentGameSection : this.gameSections) {
			allSections.add(currentGameSection);
		}			

		for (Section currentMovieSection : this.movieSections) {
			allSections.add(currentMovieSection);
		}
		
		for (Section currentDefaultSection : this.defaultSections) {
			allSections.add(currentDefaultSection);
		}
		
		return allSections;
	}
	
}
