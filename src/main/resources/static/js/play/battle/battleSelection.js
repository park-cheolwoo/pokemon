export const firstSelection = (myPokemons, idx, items) => {
    return [
        {
            text: "싸운다",
            event: {
                name: "attackList",
                data: myPokemons[idx].attacks
            }
        },
        {
            text: "포켓몬",
            event: {
                name: "pokemonList",
                data: {
					pokemons: myPokemons,
					selectionIdx: idx
				}
            }
        },
        {
            text: "아이템",
            event: {
                name: "itemList",
                data: items
            }
        },
        {
            text: "도망가기",
            event: {
                name: "exit"
            }
        }
    ];
}

export const attackSelection = (attacks) => {
    return attacks.map(attack => {
        return {
            text: attack.name,
            image: `/images/pros/type-${attack.types.originalName}.png`,
            event: {
                name: "attack",
                data: attack
            }
        }
    });
}