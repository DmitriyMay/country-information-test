$(function() {
	var getCountryByName = function() {

		$("#countryName").keypress(function (e) {

			if (e.which == 13) {
				$('#country-form').remove();

				var data = $('#countryName').serializeArray();
				var countryName = data[0]["value"];

				$.ajax({
					method: "GET",
					url: '/countries/name/' + countryName,
					headers: {
					'Cache-Control': 'no-cache, no-store, must-revalidate',
					'Pragma': 'no-cache',
					'Expires': '0'
					},
					success: function(response) {


						var domain = "";
						for (i in response.topLevelDomain) {

							domain = domain + response.topLevelDomain[i]["definition"] + ", ";

						};
						domain = domain.substring(0, domain.length - 2);

						var latlng = "";
						for (i in response.latlng) {

							latlng = latlng + response.latlng[i]["latlng"] + ", ";

						};
						latlng = latlng.substring(0, latlng.length - 2);


						var currencies = "";
						for (i in response.currencies) {

							currencies = currencies +  "code:" + response.currencies[i]["code"] + ", ";
							currencies = currencies +  "name:" + response.currencies[i]["name"] + ", ";
							currencies = currencies +  "symbol:" + response.currencies[i]["symbol"] + ", ";

						};
						currencies = currencies.substring(0, currencies.length - 2);


						var languages = "";
						for (i in response.languages) {

							languages = languages +  "iso639_1:" + response.languages[i]["iso639_1"] + ", ";
							languages = languages +  "iso639_2:" + response.languages[i]["iso639_2"] + ", ";
							languages = languages +  "name:" + response.languages[i]["name"] + ", ";
							languages = languages +  "nativeName:" + response.languages[i]["nativeName"] + ", ";

						};
						languages = languages.substring(0, languages.length - 2);


						var translations = "de:" + response.translations["de"] + ", ";
						translations = translations +  "es:" + response.translations["es"] + ", ";
						translations = translations +  "fr:" + response.translations["fr"] + ", ";
						translations = translations +  "ja:" + response.translations["ja"] + ", ";
						translations = translations +  "it:" + response.translations["it"] + ", ";
						translations = translations +  "br:" + response.translations["br"] + ", ";
						translations = translations +  "pt:" + response.translations["pt"] + ", ";
						translations = translations +  "nl:" + response.translations["nl"] + ", ";
						translations = translations +  "hr:" + response.translations["hr"] + ", ";
						translations = translations +  "fa:" + response.translations["fa"];

						var regBlocs = "";
						for (i in response.regionalBlocs) {

							regBlocs = regBlocs + "acronym:" + response.regionalBlocs[i]["acronym"] + ", ";
							regBlocs = regBlocs + "name:" +  response.regionalBlocs[i]["name"] + ", ";

							regBlocs = regBlocs + "otherAcronym:";
							for (j in response.regionalBlocs[i]["otherAcronyms"]) {
								regBlocs = regBlocs + response.regionalBlocs[i]["otherAcronyms"][j]["otherAcronym"] + ", ";
							};

							regBlocs = regBlocs + "otherName:";
							for (j in response.regionalBlocs[i]["otherNames"]) {
								regBlocs = regBlocs + response.regionalBlocs[i]["otherNames"][j]["otherName"] + ", ";
							};
						};
						regBlocs = regBlocs.substring(0, regBlocs.length - 2);


						var countryCode =
 										"<form>" +
										"<h2>Информация о странe</h2>" +
										"<div>" +
										"<label>name: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + response.name + "</a>" +
										"</div>" +

										"<div>" +
										"<label>topLevelDomain: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + domain + "</a>" +
										"</div>" +

										"<div>" +
										"<label>population: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + response.population + "</a>" +
										"</div>" +

										"<div>" +
										"<label>latlng: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + latlng + "</a>" +
										"</div>" +

										"<div>" +
										"<label>currencies: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + currencies + "</a>" +
										"</div>" +

										"<div>" +
										"<label>languages: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + languages + "</a>" +
										"</div>" +

										"<div>" +
										"<label>translations: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + translations + "</a>" +
										"</div>" +

										"<div>" +
										"<label>flag: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + response.flag + "</a>" +
										"</div>" +

										"<div>" +
										"<label>regionalBlocs: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + regBlocs + "</a>" +
										"</div>" +
										"</form>";

						$('#country').append('<div id="country-form">' + countryCode + '</div>');

					},
					error: function(response) {
					if (response.status == 404) {
						alert('Страна не найдена!');

					}
					}
				});

			}
		});
   }

   getCountryByName();



   	var getCountriesByDomain = function() {

		$("#countryDomain").keypress(function (e) {

			if (e.which == 13) {
				$('#country-form').remove();

				var data = $('#countryDomain').serializeArray();

				var countryDomain = data[0]["value"];

				$.ajax({
					method: "GET",
					url: '/countries/domain/' + countryDomain,
					headers: {
					'Cache-Control': 'no-cache, no-store, must-revalidate',
					'Pragma': 'no-cache',
					'Expires': '0'
					},
					success: function(response) {

						$('#country').append('<div id="country-form"></div>');

						for (i in response) {
							console.log(response[i]);


							var domain = "";
							for (d in response[i].topLevelDomain) {

								domain = domain + response[i].topLevelDomain[d]["definition"] + ", ";

							};
							domain = domain.substring(0, domain.length - 2);

							var latlng = "";
							for (l in response[i].latlng) {

								latlng = latlng + response[i].latlng[l]["latlng"] + ", ";

							};
							latlng = latlng.substring(0, latlng.length - 2);


							var currencies = "";
							for (c in response[i].currencies) {

								currencies = currencies +  "code:" + response[i].currencies[c]["code"] + ", ";
								currencies = currencies +  "name:" + response[i].currencies[c]["name"] + ", ";
								currencies = currencies +  "symbol:" + response[i].currencies[c]["symbol"] + ", ";

							};
							currencies = currencies.substring(0, currencies.length - 2);


							var languages = "";
							for (ln in response[i].languages) {

								languages = languages +  "iso639_1:" + response[i].languages[ln]["iso639_1"] + ", ";
								languages = languages +  "iso639_2:" + response[i].languages[ln]["iso639_2"] + ", ";
								languages = languages +  "name:" + response[i].languages[ln]["name"] + ", ";
								languages = languages +  "nativeName:" + response[i].languages[ln]["nativeName"] + ", ";

							};
							languages = languages.substring(0, languages.length - 2);


							var translations = "de:" + response[i].translations["de"] + ", ";
							translations = translations +  "es:" + response[i].translations["es"] + ", ";
							translations = translations +  "fr:" + response[i].translations["fr"] + ", ";
							translations = translations +  "ja:" + response[i].translations["ja"] + ", ";
							translations = translations +  "it:" + response[i].translations["it"] + ", ";
							translations = translations +  "br:" + response[i].translations["br"] + ", ";
							translations = translations +  "pt:" + response[i].translations["pt"] + ", ";
							translations = translations +  "nl:" + response[i].translations["nl"] + ", ";
							translations = translations +  "hr:" + response[i].translations["hr"] + ", ";
							translations = translations +  "fa:" + response[i].translations["fa"];

							var regBlocs = "";
							for (rb in response[i].regionalBlocs) {

								regBlocs = regBlocs + "acronym:" + response[i].regionalBlocs[rb]["acronym"] + ", ";
								regBlocs = regBlocs + "name:" +  response[i].regionalBlocs[rb]["name"] + ", ";

								regBlocs = regBlocs + "otherAcronym:";
								for (oa in response[i].regionalBlocs[rb]["otherAcronyms"]) {
									regBlocs = regBlocs + response[i].regionalBlocs[rb]["otherAcronyms"][oa]["otherAcronym"] + ", ";
								};

								regBlocs = regBlocs + "otherName:";
								for (on in response[i].regionalBlocs[rb]["otherNames"]) {
									regBlocs = regBlocs + response[i].regionalBlocs[rb]["otherNames"][on]["otherName"] + ", ";
								};
							};
							regBlocs = regBlocs.substring(0, regBlocs.length - 2);

							var countryCode =
										"<form>" +
										"<h2>Информация о странe</h2>" +
										"<div>" +
										"<label>name: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + response[i].name + "</a>" +
										"</div>" +

										"<div>" +
										"<label>topLevelDomain: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + domain + "</a>" +
										"</div>" +

										"<div>" +
										"<label>population: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + response[i].population + "</a>" +
										"</div>" +

										"<div>" +
										"<label>latlng: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + latlng + "</a>" +
										"</div>" +

										"<div>" +
										"<label>currencies: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + currencies + "</a>" +
										"</div>" +

										"<div>" +
										"<label>languages: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + languages + "</a>" +
										"</div>" +

										"<div>" +
										"<label>translations: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + translations + "</a>" +
										"</div>" +

										"<div>" +
										"<label>flag: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + response[i].flag + "</a>" +
										"</div>" +

										"<div>" +
										"<label>regionalBlocs: </label>" +
										"<a id=\"g_countryName\" text=\"\">" + regBlocs + "</a>" +
										"</div>" +
										"</form>";
										$('#country-form').append(countryCode);

						};
					},
					error: function(response) {
					if (response.status == 404) {
						alert('Страна не найдена!');

					}
					}
				});

			}
		});
   }

   getCountriesByDomain();

   var refreshCountries = function() {
     $("input[id=refresh-countries]").click(function() {

       $.ajax({
         method: "PUT",
         url: '/countries/',
         success: function(response) {
			$('#country-form').remove();
		 },
         error: function(response) {
         }
       });
     });
   }

   refreshCountries();
});