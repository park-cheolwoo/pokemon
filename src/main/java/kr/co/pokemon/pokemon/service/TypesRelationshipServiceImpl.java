package kr.co.pokemon.pokemon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.service.APIServiceImpl;
import kr.co.pokemon.pokemon.dao.TypesMapper;
import kr.co.pokemon.pokemon.dao.TypesRelationshipMapper;
import kr.co.pokemon.pokemon.dto.TypesDTO;
import kr.co.pokemon.pokemon.dto.TypesRelationshipDTO;
import kr.co.pokemon.pokemon.dto.TypesRelationshipDTO.DamageRelations;
import kr.co.pokemon.pokemon.dto.TypesRelationshipDTO.TypesSummary;

@Service
public class TypesRelationshipServiceImpl implements TypesRelationshipService {

	@Autowired
	private TypesRelationshipMapper typesRelationshipMapper;
	
	@Autowired
	private TypesMapper typesMapper;
	
	@Override
	public List<TypesRelationshipDTO> getAll(PageRequestDTO page) {
		return typesRelationshipMapper.selectAll(page);
	}

	@Override
	public List<TypesRelationshipDTO> getByTypeId(int id, boolean isName) {
		List<TypesRelationshipDTO> relationships = typesRelationshipMapper.selectByTypeId(id);
		
		if (!isName) {
			return relationships;
		}
		
		return relationships.stream().map(types -> {
					TypesDTO detailFromTypes = typesMapper.selectById(types.getFromId());
					TypesSummary summaryFromTypes = new TypesSummary(types.getFromId(), detailFromTypes.getName(), detailFromTypes.getImage());
					
					TypesDTO detailToTypes = typesMapper.selectById(types.getToId());
					TypesSummary summaryToTypes = new TypesSummary(types.getToId(), detailToTypes.getName(), detailToTypes.getImage());
					
					types.setFromSummary(summaryFromTypes);
					types.setToSummary(summaryToTypes);
					return types;
				}).toList();
	}

	@Override
	public TypesRelationshipDTO getById(int id) {
		return typesRelationshipMapper.selectById(id);
	}

	public TypesRelationshipDTO getByTypeIdAndTypeId(int type1, int type2) {
		Map<String, Integer> types = new HashMap<>();
		types.put("type1", type1);
		types.put("type2", type2);
		return typesRelationshipMapper.selectByTypeIdAndTypeId(types);
	}

	@Override
	public int getDataFromAPI(TypesRelationshipDTO dto) throws Exception {

		AtomicInteger count = new AtomicInteger(0);

		DamageRelations dr = dto.getDamageRelations();
		
		dr.getDoubleDamageTo().stream()
		.forEach(types -> {
			int to_id = APIServiceImpl.getIdByUrl(types.getUrl());
			dto.setFromId(dto.getId());
			dto.setToId(to_id);
			dto.setEffect(2);
			typesRelationshipMapper.insert(dto);
			count.incrementAndGet();
		});
		
		dr.getHalfDamageTo().stream()
		.forEach(types -> {
			int to_id = APIServiceImpl.getIdByUrl(types.getUrl());
			dto.setFromId(dto.getId());
			dto.setToId(to_id);
			dto.setEffect(1);
			typesRelationshipMapper.insert(dto);
			count.incrementAndGet();
		});
		
		dr.getNoDamageTo().stream()
		.forEach(types -> {
			int to_id = APIServiceImpl.getIdByUrl(types.getUrl());
			dto.setFromId(dto.getId());
			dto.setToId(to_id);
			dto.setEffect(0);
			typesRelationshipMapper.insert(dto);
			count.incrementAndGet();
		});

		return count.get();
	}

}
