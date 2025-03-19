export const firstComments = (myPokemonName, enemyName) => {
    return [
        [`앗! 야생의 ${enemyName} 이(가) 나타났다!`],
        [`가랏, ${myPokemonName} !!`, `${myPokemonName} 은(는) 무엇을 할까?`]
    ];
}

export const ingameComments = (myPokemonName) => {
	return [
		[`${myPokemonName} 은(는) 무엇을 할까?`]
	]
}

export const attackComments = (myPokemonName, enemyName, attackName, executedPower) => {
    return [
        [`${myPokemonName} 이(가) ${attackName} 공격 !`],
        [executedPower.comments, `${enemyName}는 ${executedPower.power}의 데미지를 입었다.`]
    ];
}
