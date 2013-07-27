package com.ormanli.primephonebook;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Service {

	private List<User> userlist;
	MongoTemplate mongoTemplate;
	private User selectedUser = new User();
	private String name;
	private String surname;
	private String phonenumber;
	private byte[] image;
	private StreamedContent graphicText;
	private boolean changed = false;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<User> getUserlist() {
		if ((userlist == null) || changed) {
			userlist = mongoTemplate.findAll(User.class);
			changed = false;
		}
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public StreamedContent getGraphicText() {
		return graphicText;
	}

	public void setGraphicText(StreamedContent graphicText) {
		this.graphicText = graphicText;
	}

	public void save(ActionEvent actionEvent) {
		User tempuser = new User(name, surname, phonenumber, image);
		mongoTemplate.save(tempuser);
		name = "";
		surname = "";
		phonenumber = "";
		image = new byte[]{4};
		changed = true;
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "User Saved", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}

	public void update(ActionEvent actionEvent) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "User Updated", "");

		FacesContext.getCurrentInstance().addMessage(null, message);
		mongoTemplate.save(selectedUser);
		changed = true;

	}

	public void delete(ActionEvent actionEvent) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "User Deleted", "");
		mongoTemplate.remove(selectedUser);
		FacesContext.getCurrentInstance().addMessage(null, message);
		changed = true;
	}

	public void handleFileUpload(FileUploadEvent event) {
		image = event.getFile().getContents();
		graphicText = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/png");
	}

	public StreamedContent convertToDynaImage(byte[] array) {
		StreamedContent dynaImage = new DefaultStreamedContent(new ByteArrayInputStream(array), "image/png");
		return dynaImage;
	}
}
