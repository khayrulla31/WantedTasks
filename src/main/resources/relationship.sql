SELECT
    concat(setting.familyName,' ',
       setting.name,' ',
       setting.middleName) as FIO,
 setting.birthDate as date,
 HPPersonDependant.contactRelationship as link
FROM HPPersonGeneric
JOIN
HPPersonDependant
ON
HPPersonGeneric.sysId = HPPersonDependant .HPPersonGenericSysId
JOIN
HPPersonGeneric setting
ON
HPPersonDependant.HPRelatedPersonSysId = setting.sysId
WHERE
    HPPersonGeneric.personId = 1;