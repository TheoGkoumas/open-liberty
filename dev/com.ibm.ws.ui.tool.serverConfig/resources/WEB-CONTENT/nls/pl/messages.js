/*******************************************************************************
 * Copyright (c) 2016, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

var editorMessages = {
"VALUE": "Wartość",
"SELECT": "Wybierz",
"ADD": "Dodaj",
"OK": "OK",
"CANCEL": "Anuluj",
"ADD_CHILD": "Dodawanie elementu potomnego",
"REMOVE": "Usuwanie",
"TEST" : "Testuj",
"TEST_CONNECTION": "Testuj połączenie",
"DESCRIPTION": "Opis",
"PREVIOUS": "Poprzedni",
"NEXT": "Następny",
"REMOVE_ELEMENT_CONFIRMATION": "Czy na pewno usunąć element?",
"YES": "Tak",
"NO": "Nie",
"LOADING": "Ładowanie...",
"SERVER_CONFIGURATION_EDITOR": "Narzędzie do konfigurowania serwera",
"CONFIGURATION_FILES": "Pliki konfiguracyjne",
"TOGGLE_NAVIGATION": "Przełącz nawigację",
"CLOSE": "Zamknij",
"ERROR_ACCESSING_SERVER_CONFIGURATION_FILE": "Nie można uzyskać dostępu do pliku konfiguracyjnego serwera {0}.",
"ERROR_ACCESSING_SERVER_SCHEMA_FILE": "Nie można uzyskać dostępu do pliku schematu {0}.",
"NO_MATCHES_FOUND": "Nie znaleziono dopasowań.",
"DEFAULT_VALUE_PLACEHOLDER": "{0} (wartość domyślna)",
"DEFAULT_VALUE_PLACEHOLDER_WITH_VARIABLE": "{0} (wartość domyślna) lub ${{1}} (jeśli zdefiniowano)",
"VARIABLE_VALUE_PLACEHOLDER": "{0} (jeśli zdefiniowano)",
"DEFAULT_SUFFIX": "(wartość domyślna)",
"SAVE": "Zapisz",
"DISCARD": "Odrzuć",
"ENHANCED_LABELS": "Etykiety rozszerzone",
"FIELD_DESCRIPTIONS": "Opisy pól",
"MALFORMED_XML": "Treść XML wydaje się być zniekształcona. Przełącz się na {0} na potrzeby edycji.",
"DESIGN": "Projekt",
"SOURCE": "Źródło",
"SOURCE_PANE": "panel źródłowy",
"LINE_NUMBERS": "Numery wierszy",
"DOCUMENTATION_DEFAULT": "Wartość domyślna: {0}",
"REQUIRED_SUFFIX": "(wymagane)",
"USER_NAME": "Nazwa użytkownika",
"USER_NAME_OPTIONAL": "Nazwa użytkownika (opcjonalna)",
"PASSWORD": "Hasło",
"PASSWORD_OPTIONAL": "Hasło (opcjonalne)",
"SIGN_IN": "Zaloguj",
"ONE_MOMENT_PLEASE": "Proszę czekać...",
"LOGIN_FAIL": "Logowanie nie powiodło się, spróbuj ponownie",
"FAILED" : "Niepowodzenie.",
"FAILED_HTTP_CODE" : "Żądanie nie powiodło się z kodem statusu HTTP {0}.",
"SUCCESS" : "Sukces.",
"RESPONSE": "Odpowiedź",
"PARAMETERS": "Parametry",
"MISSING_USER_NAME": "Podaj nazwę użytkownika", 
"SIGN_OUT": "Wyloguj",
"SIGN_OUT_ERROR": "Wystąpił błąd podczas próby wylogowania",
"SIGN_OUT_PROMPT": "Czy wylogować?",
"CHANGES_SAVED": "Zmiany zostały zapisane",
"FILE_ACCESS_ERROR_MESSAGE": "Wystąpił błąd podczas próby uzyskania dostępu do plików konfiguracyjnych serwera.",
"ERROR_SAVING_FILE_MESSAGE": "Zmiany nie zostały zapisane. Serwer może nie działać. Plik może być tylko do odczytu lub jego rola nie uprawnia do wprowadzania zmian w konfiguracji.",
"ERROR": "Błąd",
"UNSAVED_CHANGES_MESSAGE": "Istnieją niezapisane zmiany. ",
"READ_ONLY": "Tylko do odczytu",
"RESTRICTED_OR_UNAVAILABLE": "Zastrzeżone lub niedostępne",
"EXPLORE_INCLUDES": "Eksploruj elementy włączane",
"OPEN": "Otwórz",
"READ_ONLY_WARNING_MESSAGE": "Zdalny dostęp do plików nie jest skonfigurowany na tym serwerze. Pliki są dostępne w trybie tylko do odczytu. Aby włączyć dostęp do zapisu, dodaj następujący element do pliku server.xml:",
"SAVE_BEFORE_CLOSING_DIALOG_TITLE": "Zamykanie",
"SAVE_BEFORE_CLOSING_DIALOG_MESSAGE": "Czy zapisać zmiany w pliku {0} przed zamknięciem?",
"DONT_SAVE": "Nie zapisuj",
"RETURN_TO_EDITOR": "Wróć do edytora",
"THE_VALUE_SHOULD_BE_A_BOOLEAN": "Wartością musi być true lub false.",
"THE_VALUE_SHOULD_BE_A_NUMBER": "Wartość musi być liczbą.",
"THE_VALUE_SHOULD_BE_AMONG_THE_POSSIBLE_OPTIONS": "Wartość musi być jedną z następujących opcji: {0}.",
"UNRECOGNIZED_ELEMENT": "Element {0} nie jest rozpoznawany przez serwer. Przełącz się na {1}, aby edytować jego treść.",
"SERVER_NOT_FOUND": "Nie znaleziono serwera {0} z hostem {1} i katalogiem użytkownika {2}.",
"FILE_NOT_FOUND_REPLACE": "Nie znaleziono pliku {0}.",
"ERROR_ACCESSING_SERVER_LIST": "Nie można uzyskać dostępu do listy serwerów w kolektywie.",
"ERROR_NOT_IN_COLLECTIVE_ENVIRONMENT": "Odwołanie do serwera zdalnego w adresie URL jest niepoprawne poza środowiskiem kolektywu. Kliknij opcję {0}, aby uzyskać dostęp do plików konfiguracyjnych na serwerze lokalnym.",
"ERROR_ACCESSING_INCLUDE_FILES": "Nie można uzyskać dostępu do plików włączanych.",
"PATH_NOT_AVAILABLE": "Ścieżka jest niedostępna",
"HERE": "tutaj",
"CHANGE_SERVER": "Zmień serwer",
"ERROR_RETRIEVING_SERVER_INFORMATION": "Nie można pobrać informacji o serwerze.",
"SELECT_SERVER": "Wybór serwera",
"SERVER_DESCRIPTION": "Wybierz element z drzewa po lewej stronie, aby wyświetlić jego konfigurację.",
"SELECT_ELEMENT_TO_VIEW_DESCRIPTION": "Wybierz element, aby wyświetlić jego opis.",
"SAVING": "Zapisywanie...",
"SERVER_NAME": "Nazwa serwera",
"CLUSTER": "Klaster",
"HOST": "Host",
"USER_DIRECTORY_PATH": "Ścieżka do katalogu użytkownika",
"SERVERS": "Liczba serwerów: {0}",
"ONE_SERVER": "Liczba serwerów: 1",
"SHOWING_FIRST_N_SERVERS": "(Wyświetlanych jest pierwsze 500 serwerów)",
"COULD_NOT_RETRIEVE_SERVER_IDENTIFICATION": "Nie można pobrać informacji identyfikujących serwer.",
"CONTENT_ASSIST_AVAILABLE": "Naciśnij kombinację klawiszy CTRL+spacja, aby użyć aktywnej obsługi treści.",
"OPEN_FILE": "Otwórz plik",
"CREATE_FILE": "Utwórz plik",
"FILE_NOT_FOUND": "Nie znaleziono pliku",
"CANNOT_ACCESS_FILE": "Nie można uzyskać dostępu do pliku",
"CREATING_FILE": "Tworzenie pliku...",
"SUCCESSFULLY_CREATED_FILE": "Pomyślnie utworzono plik",
"COULD_NOT_CREATE_FILE": "Nie można utworzyć pliku",
"FILE_CHANGED_DURING_EDITING_DIALOG_TITLE": "Konflikt przy zapisie",
"OVERWRITE": "Zastąp",
"FILE_CHANGED_DURING_EDITING_DIALOG_MESSAGE": "Plik {0} został zmieniony przez innego użytkownika lub proces po jego otworzeniu.",
"OVERWRITING": "Zastępowanie...",
"SEARCH": "Szukaj",
"SETTINGS": "Ustawienia",
"LOCATION": "Położenie",
"EXPAND": "Rozwiń",
"COLLAPSE": "Zwiń",
"CLEAR": "Wyczyść",
"EXPAND_COLLAPSE": "Rozwiń/zwiń",
"ELEMENT_INFORMATION_FORM": "Formularz informacji o elemencie",
"SOURCE_EDITOR": "Edytor kodu źródłowego",
"SOURCE_EDITOR_CONTENT": "Treść edytora kodu źródłowego",
"SOURCE_EDITOR_MENU" : "Treść menu",
"ELEMENT_DESCRIPTION": "Opis elementu",
"ELEMENT_SEARCH": "Wyszukiwanie elementu",
"ADD_CHILD_ELEMENT_DIALOG": "Okno dialogowe dodawania elementu potomnego",
"REMOVE_ELEMENT_DIALOG": "Okno dialogowe usuwania elementu",
"VALIDATE_DATASOURCE_DIALOG": "Okno dialogowe Sprawdzanie poprawności połączenia ze źródłem danych",
"VALIDATE_DATASOURCE": "Testowanie połączenia ze źródłem danych",
"ENUMERATION_SELECTION_DIALOG": "Okno dialogowe wyboru wyliczenia",
"SAVE_BEFORE_CLOSING_DIALOG": "Okno dialogowe zapisywania przed zamknięciem",
"ERROR_SAVING_FILE_DIALOG": "Okno dialogowe błędu podczas zapisywania pliku",
"FILE_CHANGED_DURING_EDITING_DIALOG": "Okno dialogowe informujące o zmianie pliku podczas edytowania",
"SERVER_TABLE_CELL_FOR_SCREEN_READER": "Klaster {0}, host {1}, ścieżka do katalogu użytkownika {2}",
"WARNING": "Ostrzeżenie",
"EMPTY_STRING_ATTRIBUTE_VALUE": "(pusty łańcuch) kliknij przycisk Wyczyść, aby usunąć atrybut",
"EMPTY_STRING_ELEMENT_VALUE": "(pusty łańcuch)",
"NO_VALUE": "(brak wartości)",
"DEFAULTS": "Wartości domyślne",
"PRIMARY": "Podstawowe",
"AUTH_ALIAS_OPTIONAL": "Alias uwierzytelniania (opcjonalny)",
"AUTH_ALIAS": "Alias uwierzytelniania",
"CONTAINER_AUTHENTICATION": "Uwierzytelnianie kontenera",
"NO_RESOURCE_REFERENCE": "Brak odwołania do zasobu",
"APPLICATION_AUTHENTICATION": "Uwierzytelnianie aplikacji",
"CUSTOM_LOGIN_MODULE": "Niestandardowy moduł logowania (opcjonalny)",
"LOGIN_PROPERTIES": "Właściwości uwierzytelniania (opcjonalne)",
"TEST_RESULTS": "Wyniki testu źródła danych",
"OVERRIDES": "Opcje nadpisujące",
"SELECT_FEATURE_DIALOG": "Okno dialogowe wyboru składnika",
"SELECT_FEATURE": "Wybierz składnik",
"FEATURE_DESCRIPTION": "Opis składnika",
"SELECT_FEATURE_TO_VIEW_DESCRIPTION": "Wybierz składnik, aby wyświetlić jego opis. ",
"SUPPORT_MESSAGE" : "Konfiguracje serwerów nie są dostępne dla serwerów Node.js i serwerów w kontenerze Docker udostępnianych za pomocą kontrolera kolektywu.",
"NO_ROLE_MESSAGE": "Użytkownik nie pełni roli, która ma uprawnienia do wprowadzania zmian w konfiguracji. Pliki są dostępne w trybie tylko do odczytu. ",
	
// Messages for Collective Debugging
"REQUIRED_ACTIONS" : "WYMAGANE DZIAŁANIA DLA CZŁONKÓW KOLEKTYWU:",
"RUN_UPDATE_HOST" : "Uruchom komendę collective updateHost lub collective registerHost z parametrem --hostWritePath zawierającym ścieżkę, do której użytkownik rpcUser ma uprawnienia.  Należy także podać poprawne referencje hosta.",
"REGISTERING_HOST_LINK" : "Rejestrowanie komputerów hostów w kolektywie Liberty",
"CONIFGURED_SSH_RXA" : "Dla serwera-członka należy skonfigurować parametr SSH lub hostAuthInfo.",
"CONFIGURING_COLLECTIVE_LINK" : "Konfigurowanie kolektywu Liberty",
"CONFIGURING_RXA_LINK" : "Konfigurowanie RXA",
"TWO_LINKS" : "{0} i {1}", //Hyperlink 1 (CONFIGURING_COLLECTIVE_LINK) and hyperlink 2 (CONFIGURING_RXA_LINK)
"CONFIGRUED_READ_DIR" : "Sprawdź, czy konfiguracja członka zawiera uprawnienie dostępu remoteFileAccess readDir do plików konfiguracyjnych, które powinny być dostępne za pośrednictwem konfiguracji serwera.",
"DEFAULT_READ_DIR" : "Uwaga: Domyślnie (i gdy brak uprawnienia remoteFileAccess w konfiguracji) dostępne są następujące katalogi: ${wlp.install.dir}, ${wlp.user.dir} i ${server.output.dir}.  Domyślnie nie są dostępne katalogi writeDir.",
"PUBLISHED_READ_DIR" : "Serwer-członek musi działać lub zostać wcześniej uruchomiony, a jego katalogi remoteFileAccess readDir muszą być opublikowane.",
"JAVA_AVAILABLE" : "Sprawdź, czy w ścieżce ustawiono zmienną JAVA. Istnieją 3 sposoby ustawiania zmiennej JAVA:",
"HOST_JAVA_HOME" : "Określenie parametru --hostJAVAHome w komendzie updateHost kolektywu. Na przykład: ./collective updateHost --host=[nazwa_hosta] --user=[użytkownik] --password=[hasło] --port=[port_https] --hostJAVAHome=[ścieżka_do_zmiennej_JAVA]",
"LINK_JAVA" : "Utwórz powiązanie ze zmienną JAVA w katalogu /usr/bin. Na przykład: /usr/bin/java -> [ścieżka_do_zmiennej_JAVA]",
"JAVA_ON_PATH" : "Dodaj zmienną JAVA do zmiennej środowiskowej PATH.  W systemie Linux zmienna PATH powinna zostać wyeksportowana w pliku .bashrc.", 
"MORE_INFORMATION" : "Więcej informacji na ten temat zawierają następujące sekcje: "

};
