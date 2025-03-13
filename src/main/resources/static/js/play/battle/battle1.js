$(function() {
	const container = $(".container");
	$.ajax({
		url: '/play/create/pokemon/habitat/2',
		method: 'get',
		dataType : 'json',
		success: function (data) {
			console.log(data);
			const enemy = `
				<div class="pokemon you">
					<div class="pokemon__info">
						<div class="pokemon__info--level">LV 5</div>
						<div class="pokemon__info--name">${data.pokemon.name}</div>
						<div class="pokemon__info--hp hp-bar">
							<div class="hp-bar__value"></div>
						</div>
					</div>
					<div class="pokemon-image">
						<img src="${data.pokemon.sprites.front_default}" />
					</div>
				</div>
			`;
			
			container.append(enemy);
		},
		error: function (e) {
			console.log(e);
		}
	});
});