export const firstComments = (myPokemonName, enemyName) => {
    return [
        [`앗! 야생의 ${enemyName} 이(가) 나타났다!`],
        [`가랏, ${myPokemonName} !!`, `${myPokemonName} 은(는) 무엇을 할까?`]
    ];
}

export const attackComments = (myPokemonName, enemyName, attackName, attackPower) => {
    return [
        [`${myPokemonName} 이(가) ${attackName} 공격 !`],
        [`${enemyName}는 ${attackPower}의 데미지를 입었다.`],
        [`이제 ${myPokemonName} 은(는) 무엇을 할까?`]
    ];
}