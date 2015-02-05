ALTER TABLE MeasureDefaultRange DROP CONSTRAINT FK_MeasureDefaultRange_idMeasureDef
ALTER TABLE Goal DROP CONSTRAINT FK_Goal_idMeasureDef
ALTER TABLE Goal DROP CONSTRAINT FK_Goal_idPerson
ALTER TABLE HealthMeasureHistory DROP CONSTRAINT FK_HealthMeasureHistory_idMeasureDef
ALTER TABLE HealthMeasureHistory DROP CONSTRAINT FK_HealthMeasureHistory_idPerson
ALTER TABLE LifeStatus DROP CONSTRAINT FK_LifeStatus_idPerson
ALTER TABLE LifeStatus DROP CONSTRAINT FK_LifeStatus_idMeasureDef
DROP TABLE MeasureDefinition
DROP TABLE MeasureDefaultRange
DROP TABLE Person
DROP TABLE Goal
DROP TABLE HealthMeasureHistory
DROP TABLE LifeStatus
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
