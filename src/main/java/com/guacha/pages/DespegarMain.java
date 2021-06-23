package com.guacha.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.guacha.actions.Actions;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://www.despegar.com.co/")
public class DespegarMain extends PageObject{
	
	By departureField = By.xpath("(//input[@type='text'])[1]");
	By destinationField = By.xpath("(//input[@type='text'])[2]");
	By firstResultInList = By.xpath("//li[contains(@class, '-active')]");
	
	By dateSelector = By.xpath("(//input[@placeholder='Ida'])[1]");
	
	By calendarLeftArrow = By.xpath("(//a[@class='calendar-arrow-left'])[1]");
	By calendarRightArrow = By.xpath("(//a[@class='calendar-arrow-right'])[1]");
	By calendar = By.xpath("(//div[@data-month])[1]");
	By calendarDays = By.xpath("(//div[@data-month])/div[contains(@class, 'dates')]/div");
	By calendarSubmit = By.xpath("//div[contains(@class, 'calendar-footer')]/div/button[not(contains(@class, '-disable'))]"); //kill me pls
	
	By peopleSelector = By.xpath("(//input[@placeholder=''])[1]");
	String increasingButton = "//div[@class='stepper__room__row'][%d]/div/div/button[2]";
	String ageSelectors = "(//div[@class='stepper__room__row']//select)[%d]";
	By peopleSelectorSubmitButton = By.xpath("//div[@class='stepper__room__footer ']/a");
	
	By submitAllButton = By.xpath("(//button)[1]");
	
	public void startBrowser() {
		open();
	}
	
	public void inputCityParameters(String departure, String destination) {
		// Ingresar valor en campo origen y seleccionar el primero que aparece
		Actions.typeIntoField(getDriver(), departureField, departure);
		Actions.clickElement(getDriver(), firstResultInList);
		
		// Ingresar valor en campo destino y seleccionar el primero que aparece
		Actions.typeIntoField(getDriver(), destinationField, destination);
		Actions.clickElement(getDriver(), firstResultInList);
	}
	
	public void inputDateParameters(String date1, String date2) {
		//Dividimos la fecha en pedazos utilizables
		String[] depSplit = date1.split("/");
		String[] retSplit = date2.split("/");
		
		//Abrimos el calendario
		Actions.clickElement(getDriver(), dateSelector);
		Actions.waitForElement(getDriver(), calendarRightArrow);
		
		
		//Revisamos que el mes/año esté en el primer calendario
		//TODO: Combinar en un solo ciclo
		String[] fechaCalendario = Actions.getAttributeOfElement(getDriver(), calendar, "data-month").split("-");
		while(!fechaCalendario[0].equals(depSplit[2])) {
			if (Integer.parseInt(depSplit[2]) > Integer.parseInt(fechaCalendario[0])) {
				Actions.clickElement(getDriver(), calendarRightArrow);
			} else {
				Actions.clickElement(getDriver(), calendarLeftArrow);
			}
			fechaCalendario = Actions.getAttributeOfElement(getDriver(), calendar, "data-month").split("-");
		}
		while(!fechaCalendario[1].equals(depSplit[1])) {
			if (Integer.parseInt(depSplit[1]) > Integer.parseInt(fechaCalendario[1])) {
				Actions.clickElement(getDriver(), calendarRightArrow);
			} else {
				Actions.clickElement(getDriver(), calendarLeftArrow);
			}
			fechaCalendario = Actions.getAttributeOfElement(getDriver(), calendar, "data-month").split("-");
		}
		
		//Buscamos el botón para el día de ida
		List<WebElement> dias = Actions.getElements(getDriver(), calendarDays);
		
		//Variables de control para verificar en que paso vamos
		boolean departureClicked = false;
		boolean bothClicked = false;
		
		//Mientras que no se hayan seleccionado las dos fechas
		while(!bothClicked) {
			// Recorremos el calendario de la izquierda
			for (WebElement e : dias) {
				// Si no hemos seleccionado la primera fecha, y es match, le hacemos click
				if (!departureClicked && e.getText().equals(depSplit[0])) {
					Actions.clickElement(getDriver(), e);
					departureClicked = true;
				}
				// Si ya seleccionamos la primera fecha, y hay match en la segunda, le hacemos click
				if (departureClicked && e.getText().equals(retSplit[0])) {
					if (fechaCalendario[1].equals(retSplit[1])) {
						Actions.clickElement(getDriver(), e);
						bothClicked = true;		
						break;
					}
				}
			}
			if (!bothClicked) {
				Actions.clickElement(getDriver(), calendarRightArrow);
				fechaCalendario = Actions.getAttributeOfElement(getDriver(), calendar, "data-month").split("-");				
			}
		}
		Actions.clickElement(getDriver(), calendarSubmit);
	}
	
	public void inputPeopleParameters(int adults, int children, String[] childrenAges) {
		// Obtener dinámicamente los botones de incrementar adultos y niños
		Actions.clickElement(getDriver(), peopleSelector);
		By increaseAdultsButton = By.xpath(String.format(increasingButton, 1));
		By increaseChildrenButton = By.xpath(String.format(increasingButton, 2));
		
		Actions.waitForElement(getDriver(), increaseAdultsButton);
		
		// Hacer click la cantidad de veces respectiva
		for (int i = 1; i < adults; i++) {
			Actions.clickElement(getDriver(), increaseAdultsButton);			
		}
		
		for (int i = 0; i < children; i++) {
			Actions.clickElement(getDriver(), increaseChildrenButton);			
		}
		
		// Seleccionar la edad de los niños
		for (int i = 1; i <= children; i++) {

			By ageSelector = By.xpath(String.format(ageSelectors, i));
			Actions.typeIntoField(getDriver(), ageSelector, childrenAges[i-1]);
		}
		Actions.clickElement(getDriver(), peopleSelectorSubmitButton);
	}
	
	public void submitEverything() {
		Actions.clickElement(getDriver(), submitAllButton);
	}
	
}
