package com.backinformal.BackInFormal_Backend.DTO;

import java.util.ArrayList;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 
public class DeleteItemsDTO {

//	private List<ItemDTO> itemsList = new ArrayList<>();
	private List<Long> itemsIDsList = new ArrayList<>();

public DeleteItemsDTO(List<Long> itemsIDsList) {
	super();
	this.itemsIDsList = itemsIDsList;
}

public DeleteItemsDTO() {
	super();
	// TODO Auto-generated constructor stub
}

public List<Long> getItemsIDsList() {
	return itemsIDsList;
}

public void setItemsIDsList(List<Long> itemsIDsList) {
	this.itemsIDsList = itemsIDsList;
}
	
}